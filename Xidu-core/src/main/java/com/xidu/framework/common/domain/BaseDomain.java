/************************************************************************************
 * @File name   :      BaseDomain.java
 *
 * @Author      :      Brenda Yin
 *
 * @Date        :      2011-2-16
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
 * 2011-1-26 上午10:15:14         Brenda Yin            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.xidu.framework.common.constant.CommonConstants;
import com.xidu.framework.common.util.Utils;

/**
 * Base class of all domain.Include common fields of all domain. And it will be
 * extended by specific domains.
 * 
 */
@MappedSuperclass
public abstract class BaseDomain implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3909854925255534858L;

	// the primary id of domain
    protected Long id;

    // the flag indicating if this record is deleted or not
    // protected int isDeleted;

    // creator of current record
    protected long createBy;

    // protected Date createDate
    protected Date createDate;

    // modifier id of last modifier
    protected long lastUpdateBy;

    // protected Date updateDate;
    protected Date lastUpdateDate;

    
    protected long deleteFlag;
    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see java.lang.Object#toString()
     * 
     */
    public String toString() {
        return ", id=" + id + ", createBy=" + createBy + ", createDate="
            + Utils.formatDate(CommonConstants.DATE_FORMATE_YYMMDD, createDate) 
            + ",lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate="
            + Utils.formatDate(CommonConstants.DATE_FORMATE_YYMMDD, lastUpdateDate) + "]";
    }

    /**
     * get isDeleted flag
     * 
     * @Date : 2011-3-23
     * @return int value. 0 represents undeleted. 1 represents deleted.
     */
    /*
     * @Column(name = "IS_DELETED", nullable = false) public Integer
     * getDeleted() { return this.isDeleted; }
     */

    /**
     * set int value to field isDeleted
     * 
     * @Date : 2011-3-23
     * @param isDeleted
     *            - indicates if current record is deleted.
     */
    /*
     * public void setDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
     */

    /**
     * get create date of domain.
     * 
     * @Date : 2011-3-23
     * @return the create date of domain
     */
    @Column(name = "CREATE_DATE", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set create date of domain
     * 
     * @Date : 2011-3-23
     * @param createDate
     *            - indicates create date of the record.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * set creator of domain
     * 
     * @Date : 2011-3-16
     * 
     * @return the creator id of domain
     */
    @Column(name = "CREATE_BY")
    public long getCreateBy() {
        return createBy;
    }

    /**
     * set creator id of domain
     * 
     * @Date : 2011-3-16
     * 
     * @param createBy
     *            creator id of domain
     */
    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    /**
     * get modifier id of domain
     * 
     * @Date : 2011-3-16
     * 
     * @return the modifier id of domain
     */
    @Column(name = "LAST_UPDATE_BY")
    public long getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * set modifier id of domain
     * 
     * @Date : 2011-3-16
     * 
     * @param lastUpdateBy
     *            modifier id of domain
     */
    public void setLastUpdateBy(long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * get update date of domain
     * 
     * @Date : 2011-3-16
     * 
     * @return the update date of domain
     */
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * set update date of domain
     * 
     * @Date : 2011-3-16
     * 
     * @param lastUpdateDate
     *            Date of domain
     * 
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name="DELETE_FLAG")
	public long getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(long deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
