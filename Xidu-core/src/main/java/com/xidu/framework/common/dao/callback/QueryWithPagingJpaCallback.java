/************************************************************************************
 * @File name   :      QueryWithPagingJpaCallback.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-3-9
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who                 Version         Comments
 * 2011-3-9 上午10:04:14         Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

import com.xidu.framework.common.util.Pager;

/**
 *JpaCallback's implementation of JPA's Query with Paging support.
 * 
 * @param <T>
 *            - domain type
 */
public class QueryWithPagingJpaCallback<T> implements JpaCallback<List<T>> {

    // JP-Query string
    private String queryStr;
    // JP-Query count string
    private String countQueryStr;
    // instance of Pager
    private Pager pager;

    /**
     * Construct an instance of QueryWithPagingJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param countQueryStr
     *            - JP-Query count string
     * @param pager
     *            - instance of Pager
     */
    public QueryWithPagingJpaCallback(String queryStr, String countQueryStr, Pager pager) {
        this.queryStr = queryStr;
        this.countQueryStr = countQueryStr;
        this.pager = pager;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see org.springframework.orm.jpa.JpaCallback#doInJpa(javax.persistence.EntityManager)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> doInJpa(EntityManager em) throws PersistenceException {
        Query query = em.createQuery(queryStr);

        Query countQuery = em.createQuery(countQueryStr);
        int actualSize = Integer.parseInt(countQuery.getSingleResult().toString());
        if (null != pager) {
            pager.rebuild(actualSize);
            if((pager.getCurrentPage() - 1) * pager.getPageSize()==-1){
            	 query.setFirstResult(1);
            }else{
                query.setFirstResult((pager.getCurrentPage() - 1) * pager.getPageSize());
            }
            query.setMaxResults(pager.getPageSize());
        }
        return query.getResultList();
    }

}
