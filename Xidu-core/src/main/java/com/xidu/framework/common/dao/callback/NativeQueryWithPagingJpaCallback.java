/************************************************************************************
 * @File name   :      NativeQueryWithPagingJpaCallback.java
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
 * 2011-3-9 上午10:15:25        Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

import com.xidu.framework.common.util.Pager;

/**
 *JpaCallback's implementation of Native Query with paging support.
 *@param <T> - domain type
 */
public class NativeQueryWithPagingJpaCallback<T> implements JpaCallback<List<T>> {

    // native query sql
    private String nativeQuerySql;
    // native count sql
    private String countNativeQuerySql;
    // instance of Pager
    private Pager pager;

    /**
     * construct an instance of NativeQueryWithPagingJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql
     * @param countNativeQuerySql
     *            - native count sql
     * @param pager
     *            - instance of Pager
     */
    public NativeQueryWithPagingJpaCallback(String nativeQuerySql, String countNativeQuerySql,
        Pager pager) {
        this.nativeQuerySql = nativeQuerySql;
        this.countNativeQuerySql = countNativeQuerySql;
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
        Query nativeQuery = em.createNativeQuery(nativeQuerySql);

        Query countNativeQuery = em.createNativeQuery(countNativeQuerySql);
        int actualSize = Integer.parseInt(countNativeQuery.getSingleResult().toString());
        if (null != pager) {
            pager.rebuild(actualSize);
            nativeQuery.setFirstResult((pager.getCurrentPage() - 1) * pager.getPageSize());
            nativeQuery.setMaxResults(pager.getPageSize());
        }
        return nativeQuery.getResultList();
    }

}
