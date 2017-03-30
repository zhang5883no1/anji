/************************************************************************************
 * @File name   :      BasePagerDto.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-3-11
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who                 Version          Comments
 * 2011-3-11 下午04:21:16            Jianxi Wu            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dto;

import java.util.List;

import com.xidu.framework.common.dto.BaseDto;
import com.xidu.framework.common.util.Pager;

/**
 * Provide paging support. All Dto which needs paging support will extend it.
 */
public class BasePagerDto<T> extends BaseDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5628075185898802523L;
	// instance of Pager.
    private Pager pager=new Pager();
    
    //returnList
    private List<T> resultList;
    
     //sort column
    private String sortColumn;
    
    private String ascOrDesc;

    /**
     * get instance of Pager.
     * 
     * @Date : 2011-3-11
     * 
     * @return the instance of Pager
     */
    public Pager getPager() {
        return pager;
    }

    /**
     * set pager instance which includes paging information.
     * 
     * @Date : 2011-3-11
     * 
     * @param pager
     *            the pager to set
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getAscOrDesc() {
		return ascOrDesc;
	}

	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}


}
