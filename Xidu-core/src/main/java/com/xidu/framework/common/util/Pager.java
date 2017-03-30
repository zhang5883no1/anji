/************************************************************************************
 * @File name   :      PagingObject.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-3-4
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who             Version            Comments
 * 2011-3-4 下午03:44:39         Jianxi Wu        1.0               Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

/**
 * Class including Paging information.
 */
public class Pager {
    // current page no.
    private int currentPage = 1;
    // page size
    private int pageSize = 25;
    // total pages
    private int totalPages;
    // total record size
    private int totalSize;


    /**
     * get current page number.
     * 
     * @Date : 2011-3-4
     * 
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * set current page number.
     * 
     * @Date : 2011-3-4
     * 
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * get page size
     * 
     * @Date : 2011-3-4
     * 
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * set page size
     * 
     * @Date : 2011-3-4
     * 
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * get total pages
     * 
     * @Date : 2011-3-4
     * 
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * set total pages
     * 
     * @Date : 2011-3-4
     * 
     * @param totalPages
     *            the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * get total size
     * 
     * @Date : 2011-3-4
     * 
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * set total size
     * 
     * @Date : 2011-3-4
     * 
     * @param totalSize
     *            the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * According to total size and page size, rebuild current page and total
     * pages.
     * 
     * @Date : 2011-3-23
     * @param totalSize
     *            - total size of records
     */
    public void rebuild(int totalSize) {
        setTotalSize(totalSize);
        setTotalPages((totalSize - 1) / getPageSize() + 1);
        if (this.currentPage < 1) {
            setCurrentPage(1);
        }
        if (this.currentPage > this.totalPages) {
            setCurrentPage(this.totalPages);
        }
    }

	public int getCurrentPageFormRow() {
		return (currentPage-1) * pageSize + 1;
	}

	public int getCurrentPageToRow() {
		int currentPageRows=pageSize;
		//if the last page
		if(currentPage==totalPages && totalSize % pageSize!=0){
			currentPageRows=totalSize % pageSize;
		}
		return (currentPage-1) * pageSize  + currentPageRows;
	}
    

}
