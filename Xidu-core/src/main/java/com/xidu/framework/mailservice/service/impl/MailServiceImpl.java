/************************************************************************************
 * @File name   :      MailServiceImpl.java
 *
 * @Author      :      YIFEGU
 *
 * @Date        :      2011-3-14
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2011-3-14 上午09:48:27			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.service.impl;

import java.util.HashMap;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.mailservice.constant.MailConfConstant;
import com.xidu.framework.mailservice.constant.MailServiceErrorConstants;
import com.xidu.framework.mailservice.domain.Mail;
import com.xidu.framework.mailservice.domain.MailServiceConf;
import com.xidu.framework.mailservice.domain.MailTemplate;
import com.xidu.framework.mailservice.service.IMailService;
import com.xidu.framework.mailservice.utils.MailUtil;
import com.xidu.framework.mailservice.utils.PropertiesUtil;

/**
 * IMailService Implementation Class
 */
public class MailServiceImpl implements IMailService {

    private static Session session = null;
    private static MailServiceConf mailServiceConf = null;
    private static Logger logger = Logger.getLogger(MailServiceImpl.class);

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.mailservice.service.IMailService#initMailService()
     * 
     */
    @Override
    public boolean initMailService() {

        MailServiceConf mailServiceConf = new MailServiceConf();
        mailServiceConf.setHost(PropertiesUtil.getProperties(MailConfConstant.MAIL_SERVICE_HOST));
        mailServiceConf.setUsername(PropertiesUtil
            .getProperties(MailConfConstant.MAIL_SERVICE_USERNAME));
        mailServiceConf.setPassword(PropertiesUtil
            .getProperties(MailConfConstant.MAIL_SERVICE_PASSWORD));
        Long port = 25L;
        String tmp = PropertiesUtil.getProperties(MailConfConstant.MAIL_SERVICE_PORT);
        if (tmp != null && !tmp.equals("")) {
            try {
                port = Long.parseLong(tmp);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        mailServiceConf.setPort(port);

        this.mailServiceConf = mailServiceConf;
        session = MailUtil.getSession(this.mailServiceConf);
        System.out.println(session.getProperty("mail.smtp.port"));
        System.out.println(session.getProperty("mail.smtp.auth"));
        System.out.println(session.getProperty("mail.smtp.host"));
        if (session != null) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @throws AppException
     * @Date : 2011-4-18
     * @see com.xidu.framework.mailservice.service.IMailService#initMailServiceJNDI()
     **/
    @Override
    public boolean initMailServiceJNDI() throws AppException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            session = (Session) envCtx.lookup(PropertiesUtil
                .getProperties(MailConfConstant.MAIL_SERVICE_SESSION_JNDI_NAME));
        } catch (NamingException e) {
            e.printStackTrace();
            String errMsg = "Get Mail Session From JNDI Exception!";
            throw new AppException(MailServiceErrorConstants.GET_MAIL_SESSION_FROM_JNDI_EXCEPTION,
                errMsg, e.getCause());

        }
        return true;

    }

    /**
     * Get Session
     */
    private Session getSession() throws AppException {
        if (session == null) {
            initMailServiceJNDI();
        }
        return session;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.mailservice.service.IMailService#sendMail(com.xidu.framework.mailservice.domain.Mail)
     * 
     */
    @Override
    public boolean sendMail(Mail mail) {
        boolean sentStatus = sendMail(mail, (MailTemplate) null);
        return sentStatus;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.mailservice.service.IMailService#sendMail(com.xidu.framework.mailservice.domain.Mail,
     *      java.lang.String, HashMap)
     * 
     */
    @Override
    public boolean sendMail(Mail mail, String templateName, HashMap<String, String> editPointsMap) {

        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setTemplateName(templateName);
        mailTemplate.setEditPointMap(editPointsMap);
        boolean sentStatus = sendMail(mail, mailTemplate);
        return sentStatus;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.mailservice.service.IMailService#sendMail(com.xidu.framework.mailservice.domain.Mail,
     *      com.xidu.framework.mailservice.domain.MailTemplate)
     * 
     */
    @Override
    public boolean sendMail(Mail mail, MailTemplate mailTemplate) {

        try {
            String mailContent;
            MimeMessage msg = new MimeMessage(getSession());
            // Create an MimeMultipart Object to store multiple BodyPart Object
            Multipart multipart = new MimeMultipart();
            // Set the mail content
            // Create a BodyPart object to store mail content
            BodyPart mdp = new MimeBodyPart();
            // mailContent = getFormatedContent();

            if (mailTemplate != null) {
                mailContent = MailUtil.getFormatedContentByTemplate(mailTemplate);
            } else {
                mailContent = mail.getMailContent();
            }
            // Set the content and format/encoding for BodyPart Object
            mdp.setContent(mailContent, mail.getEmailType() + ";charset=UTF-8");
            mdp.setHeader("Content-Transfer-Encoding", "8bit");
            // Add the BodyPart which contains mail content to MimeMultiPart
            // Object
            multipart.addBodyPart(mdp);
            if (mail.getAttachmentsList() != null && mail.getAttachmentsList().size() > 0) {
                multipart = MailUtil.addAttachmentsToBodyPart(multipart, mail.getAttachmentsList());
            }

            msg.setFrom(new InternetAddress(mail.getMailFrom()));
            msg = MailUtil
                .addRecipients(msg, mail.getMailTo(), mail.getMailCc(), mail.getMailBcc());
            msg.setSubject(mail.getMailSubject());
            msg.setContent(multipart);

            Transport transport = session.getTransport(PropertiesUtil
                .getProperties(MailConfConstant.MAIL_SERVICE_PROTOCOL_TYPE));
            if ((mailServiceConf.getUsername() == null || "".equals(mailServiceConf.getUsername()))
                || (mailServiceConf.getPassword() == null || "".equals(mailServiceConf
                    .getPassword()))) {
                transport.connect();
            } else if(mailServiceConf.getPort()!=null) {
            	transport.connect(mailServiceConf.getHost(), mailServiceConf.getPort().intValue(), mailServiceConf.getUsername(), mailServiceConf.getPassword());
              
            }else{
           transport.connect(mailServiceConf.getHost(), mailServiceConf.getUsername(),mailServiceConf.getPassword());
            }
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            logger.info("Mail Send Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.mailservice.service.IMailService#sendMail(com.xidu.framework.mailservice.domain.Mail,
     *      java.lang.String)
     * 
     */
    @Override
    public boolean sendMail(Mail mail, String templateName) {

        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setTemplateName(templateName);
        boolean sentStatus = sendMail(mail, mailTemplate);
        return sentStatus;

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-13
     * @see com.xidu.framework.mailservice.service.IMailService#sendMailInline(com.xidu.framework.mailservice.domain.Mail,
     *      com.xidu.framework.mailservice.domain.MailTemplate,
     *      java.util.HashMap)
     **/
    @Override
    public boolean sendMailInline(Mail mail, MailTemplate mailTemplate,
        HashMap<String, String> imgUrlMap) {

        try {
            String mailContent = "";
            MimeMessage msg = new MimeMessage(getSession());
            // Create an MimeMultipart Object to store multiple BodyPart Object
            Multipart multipart = new MimeMultipart("related");
            // Set the mail content
            // Create a BodyPart object to store mail content
            BodyPart mdp = new MimeBodyPart();
            // mailContent = getFormatedContent();

            if (mailTemplate != null) {
                mailContent = MailUtil.getFormatedContentByTemplate(mailTemplate);
            } else {
                mailContent = mail.getMailContent();
            }
            // Set the content and format/encoding for BodyPart Object
            mdp.setContent(mailContent, mail.getEmailType() + ";charset=UTF-8");
            mdp.setHeader("Content-Transfer-Encoding", "8bit");
            // Add the BodyPart which contains mail content to MimeMultiPart
            // Object
            multipart.addBodyPart(mdp);
            if (mail.getAttachmentsList() != null && mail.getAttachmentsList().size() > 0) {
                multipart = MailUtil.addAttachmentsToBodyPart(multipart, mail.getAttachmentsList());
            }

            msg.setFrom(new InternetAddress(mail.getMailFrom()));
            msg = MailUtil
                .addRecipients(msg, mail.getMailTo(), mail.getMailCc(), mail.getMailBcc());
            msg.setSubject(mail.getMailSubject());

            multipart = MailUtil.addImagesToBodyPart(multipart, imgUrlMap);
            msg.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            if ((mailServiceConf.getUsername() == null || "".equals(mailServiceConf.getUsername()))
                || (mailServiceConf.getPassword() == null || "".equals(mailServiceConf
                    .getPassword()))) {
                transport.connect();
            } else {
                transport.connect(mailServiceConf.getHost(), mailServiceConf.getUsername(),
                    mailServiceConf.getPassword());
            }
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            logger.info("Mail Send Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
