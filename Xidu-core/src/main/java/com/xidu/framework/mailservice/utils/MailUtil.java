/************************************************************************************
 * @File name   :      MailUtil.java
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
 * Date                             Who                 Version             Comments
 * 2011-3-14上午09:54:59          YIFEGU          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.mailservice.constant.MailConfConstant;
import com.xidu.framework.mailservice.constant.MailServiceErrorConstants;
import com.xidu.framework.mailservice.domain.Attachment;
import com.xidu.framework.mailservice.domain.MailServiceConf;
import com.xidu.framework.mailservice.domain.MailTemplate;

/**
 *  Utility Class for Mail Service
 */
public class MailUtil {
    
    /**
     * Get properties
     * @Date        :      2011-4-6
     * @param host host
     * @return Properties instance
     */
    private static Properties getProps(String host,Long port){
        System.out.println("port====================="+port);
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        return props;
        
    }
    
    /**
     * Get Session
     * @Date        :      2011-4-6
     * @param mailServiceConf mail
     * @return Session Instance
     */
    public static Session getSession(MailServiceConf mailServiceConf){
        if(!isMailConfValid(mailServiceConf)){
            return null;
        }
        String host = mailServiceConf.getHost();
        Session session = Session.getDefaultInstance(getProps(host,mailServiceConf.getPort()));
        return session;
        
    }
    
    /**
     * Get Formated Content By Template
     * @Date        :      2011-4-6
     * @param mailTemplate instance
     * @return Formated Content
     * @throws AppException 
     *  
     */
    public static String getFormatedContentByTemplate(MailTemplate mailTemplate) throws AppException{
        
        String templateContent = readMailTemplate(mailTemplate.getTemplateName());
        String formattedContent = MailTextFormat.formatMailByTemplate(templateContent, mailTemplate.getEditPointMap());
        return formattedContent;
    
    }
    
    /**
     * Get mail template content
     * @Date        :      2011-4-6
     * @param templateName template name
     * @return template content
     * @throws AppException 
     * @throws Exception 
     */
    public static String readMailTemplate(String templateName) throws AppException{
        
        StringBuffer mailtemplate = new StringBuffer();
        try {
            String templateLocation = PropertiesUtil.getProperties(MailConfConstant.MAIL_SERVICE_TEMPLATE_LOCATION);
            String templateType = PropertiesUtil.getProperties(MailConfConstant.MAIL_SERVICE_TEMPLATE_TYPE);
            File read = new File(templateLocation + templateName + templateType);
            BufferedReader br = new BufferedReader(new FileReader(read));
            String contentline = br.readLine();
            while(contentline != null){
                
                mailtemplate.append(contentline);
                contentline = br.readLine();
                
            }
            br.close();
        } catch (Exception e) {
            String errMsg = "Read Mail Template Exception!";
            throw new AppException(MailServiceErrorConstants.READ_MAIL_TEMPLATE_EXCEPTION, errMsg, e.getCause());            

        }
        return mailtemplate.toString();
                
    }
    
    /**
     * Add attachments to body part
     * @Date        :      2011-4-6
     * @param multipart instance
     * @param attachmentsList instance
     * @return Multipart instance
     * @throws Exception 
     */
    public static Multipart addAttachmentsToBodyPart(Multipart multipart,List<Attachment> attachmentsList) throws Exception{
        Iterator<Attachment> it = attachmentsList.iterator();
        Attachment attachment = null;
        while(it.hasNext()){
            attachment = (Attachment)it.next();
            BodyPart mdp=new MimeBodyPart();
            String attachmentPath = attachment.getFilepath();
            System.out.println(attachmentPath+"kkkkkkkkkkkkkkkkkkkkkk");
            FileDataSource fds = new FileDataSource(attachmentPath);
            DataHandler dh = new DataHandler(fds);
            String filename = attachment.getFilename();
            System.out.println(filename+"000000000000000000000000");
            if(Utils.isNullOrEmpty(filename)){
                int fileNameIndex=attachmentPath.lastIndexOf(File.separator);
                System.out.println(fileNameIndex+"99999999999999999");
                filename = attachmentPath.substring(fileNameIndex);//Get the attachment file name 
            }
            
            //String formatedFileName=new String(filename.getBytes("gb2312"),"utf-8");// Handle the Chinese file name
            String formatedFileName = MimeUtility.decodeText(filename);
            formatedFileName = MimeUtility.encodeWord(formatedFileName, "utf-8", null);
            //String formatedFileName = MimeUtility.encodeWord(filename, "utf-8", null);
            
            mdp.setFileName(formatedFileName);//the Display file name can be different with the original name, but it's better to keep same
            mdp.setDataHandler(dh);
            multipart.addBodyPart(mdp);
            
        }
        return multipart;
        
        
        
    }
    
    /**
     * Add Mail Recipients 
     * @Date        :      2011-4-6
     * @param msg MimeMessage instance
     * @param mailTo List
     * @param mailCc List
     * @param mailBcc List
     * @return MimeMessage Instance
     * @throws AddressException
     * @throws MessagingException
     */
    public static MimeMessage addRecipients(MimeMessage msg, List<String> mailTo, List<String> mailCc, List<String> mailBcc) throws AddressException, MessagingException{
        if(mailTo != null && mailTo.size() > 0){
            Iterator<String> mailToIt = mailTo.iterator();
            String toAddress = "";
            while(mailToIt.hasNext()){
                toAddress = (String)mailToIt.next();
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));         
            }
        }
        if(mailCc != null && mailCc.size() > 0){
            Iterator<String> mailCcIt = mailCc.iterator();
            String ccAddress = "";
            while(mailCcIt.hasNext()){
                ccAddress = (String)mailCcIt.next();
                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
            }
        }
        if(mailBcc != null && mailBcc.size() > 0){
            Iterator<String> mailBccIt = mailBcc.iterator();            
            String bccAddress = "";
            while(mailBccIt.hasNext()){
                bccAddress = (String)mailBccIt.next();
                msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress));
            }
        }       
        return msg;     
    }
    
    /**
     * Check whether the Mail Configuration has been set correctly
     * @Date        :      2011-4-6
     * @param mailServiceConf instance
     * @return true/false to identify the valid/invalid result
     */
    public static boolean isMailConfValid(MailServiceConf mailServiceConf){
    	System.out.println(mailServiceConf.getPassword());
    	System.out.println(mailServiceConf.getUsername());
        if(Utils.notEmpty(mailServiceConf.getHost())){
            if(Utils.notEmpty(mailServiceConf.getUsername())){
                if(Utils.notEmpty(mailServiceConf.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Multipart addImagesToBodyPart(Multipart multipart, HashMap<String,String> imgUrlMap) throws IOException, MessagingException{
       InputStream is;
       DataSource fileDataSource;
       MimeBodyPart imagePart;
       Set<String> cidSet = imgUrlMap.keySet();
       Iterator<String> it = cidSet.iterator();
       while(it.hasNext()){
           Object cid = it.next();
           is = new FileInputStream((String)imgUrlMap.get(cid));
           fileDataSource = new ByteArrayDataSource(is,"application/octet-stream");
           //add image into another part
           imagePart = new MimeBodyPart();
           imagePart.setDataHandler(new DataHandler(fileDataSource));
           //assign a cid to the image
           imagePart.setHeader("Content-ID", (String)cid);
           multipart.addBodyPart(imagePart);
       }
       return multipart; 
    }

}
