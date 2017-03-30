/************************************************************************************
 * @File name   :      FileTransferLogDto.java
 *
 * @Author      :      junyi cai
 *
 * @Date        :      2011-3-7
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who         Version        Comments
 * 2011-3-7 下午01:27:49        junyi cai     1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.file.dto;

import java.util.Date;

/**
 * This is the dto for file transfer in order to query log.
 */
public class FileTransferLogDto {

    private String fileName;
    private int type;
    private String status;
    private Date startTime;
    private Date endTime;

    /**
     * Get the file name
     * 
     * @Date : 2011-3-7
     * 
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the file name
     * 
     * @Date : 2011-3-7
     * 
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the file transfer type parameter
     * 
     * @Date : 2011-3-7
     * 
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Set the file transfer type parameter
     * 
     * 
     * @Date : 2011-3-7
     * 
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Get the file transfer status parameter
     * 
     * @Date : 2011-3-7
     * 
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @Author : caijunyi
     * 
     * @Date : 2011-3-7
     * 
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the file transfer start time parameter
     * 
     * @Date : 2011-3-7
     * 
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Set the file transfer start time parameter
     * 
     * @Date : 2011-3-7
     * 
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the file transfer end time parameter
     * 
     * @Date : 2011-3-7
     * 
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Set the file transfer end time parameter
     * 
     * @Date : 2011-3-7
     * 
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
