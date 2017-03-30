/************************************************************************************
 * @File name   :      NamedQueryWithPagingJpaCallback.java
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
 * 2011-3-9 上午10:39:35         Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

import com.xidu.framework.common.util.Pager;

/**
 *NamedQuery with Paging Support.
 * 
 * @param <T>
 *            indicate domain type
 */
public class NamedQueryWithPagingJpaCallback<T> implements JpaCallback<List<T>> {

    // NamedQuery's or NamedNativeQuery's name
    private String queryName;
    // count NamedQuery's or NamedNativeQuery's name
    private String countQueryName;
    // instance of Pager
    private Pager pager;

    /**
     * construct an instance of NamedQueryWithPagingJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param queryName - query name
     * @param countQueryName - count query name
     * @param pager - pager instance
     */
    public NamedQueryWithPagingJpaCallback(String queryName, String countQueryName, Pager pager) {
        this.queryName = queryName;
        this.countQueryName = countQueryName;
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
        Query namedQuery = em.createNamedQuery(queryName);

        Query countNamedQuery = em.createNamedQuery(countQueryName);
        int actualSize = Integer.parseInt(countNamedQuery.getSingleResult().toString());
        if (null != pager) {
            pager.rebuild(actualSize);
            namedQuery.setFirstResult((pager.getCurrentPage() - 1) * pager.getPageSize());
            namedQuery.setMaxResults(pager.getPageSize());
        }
        return namedQuery.getResultList();
    }

}
