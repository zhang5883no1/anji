/************************************************************************************
 * @File name   :      NativeQueryByPagingAndRsmJpaCallback.java
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
 * 2011-3-9 下午04:18:56         Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

import com.xidu.framework.common.util.Pager;

/**
 *JpaCallback's implementation of NativeQuery with Paging Support and sql
 * result set mapping.
 * 
 * @param <T>
 *            - JP-Query string
 */
public class NativeQueryByPagingAndRsmJpaCallback<T> implements JpaCallback<List<T>> {

    // native query sql
    private String nativeQuerySql;
    // count native query sql
    private String countNativeQuerySql;
    // instance of Pager
    private Pager pager;
    // sql result set mapping's name
    private String sqlResultSetMappingName;

    /**
     * construct an instance of NativeQueryByPagingAndRsmJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql
     * @param countNativeQuerySql
     *            - native count sql
     * @param pager
     *            - instance of Pager
     * @param sqlResultSetMappingName
     *            - sql result set mapping's name
     */
    public NativeQueryByPagingAndRsmJpaCallback(String nativeQuerySql, String countNativeQuerySql,
        Pager pager, String sqlResultSetMappingName) {
        this.nativeQuerySql = nativeQuerySql;
        this.countNativeQuerySql = countNativeQuerySql;
        this.pager = pager;
        this.sqlResultSetMappingName = sqlResultSetMappingName;
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
        Query nativeQuery = em.createNativeQuery(nativeQuerySql, sqlResultSetMappingName);

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
