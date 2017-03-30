/************************************************************************************
 * @File name   :      Mail.java
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
 * 2011-3-14 上午09:35:51			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Mail Domain Class
 */
public class Mail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mailFrom;
	private List<String> mailTo;
	private List<String> mailCc;
	private List<String> mailBcc;
	private String mailSubject;
	private String mailContent;
	private String emailType = "text/html";
	private List<Attachment> attachmentsList;
	
	/**
	 * Get Mail From
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * Set Mail From
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * Get Mail To
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailTo
	 */
	public List<String> getMailTo() {
		return mailTo;
	}
	/**
	 * Set Mail To
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailTo the mailTo to set
	 */
	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}
	/**
	 * Get Mail Cc
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailCc
	 */
	public List<String> getMailCc() {
		return mailCc;
	}
	/**
	 * Set Mail Cc
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailCc the mailCc to set
	 */
	public void setMailCc(List<String> mailCc) {
		this.mailCc = mailCc;
	}
	/**
	 * Get Mail Bcc
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailBcc
	 */
	public List<String> getMailBcc() {
		return mailBcc;
	}
	/**
	 * Set Mail Bcc
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailBcc the mailBcc to set
	 */
	public void setMailBcc(List<String> mailBcc) {
		this.mailBcc = mailBcc;
	}
	/**
	 * Get Mail Subject
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}
	/**
	 * Set Mail Subject
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	/**
	 * Get Mail Content
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}
	/**
	 * Set Mail Content
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param mailContent the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	/**
	 * Get Email Type
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the emailType
	 */
	public String getEmailType() {
		return emailType;
	}
	/**
	 * Set Email Type
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param emailType the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	/**
	 * Get attachmentsList
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the attachmentsList
	 */
	public List<Attachment> getAttachmentsList() {
		return attachmentsList;
	}
	/**
	 * Set attachmentsList
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param attachmentsList the attachmentsList to set
	 */
	public void setAttachmentsList(List<Attachment> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}
	

}
