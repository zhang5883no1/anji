/************************************************************************************
 * @File name   :      NativeQueryWithResultSetMappingJpaCallback.java
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
 * 2011-3-9 下午04:08:45         Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

/**
 * JpaCallback's implementation of Native query with Paging support and using
 * sql result set mapping.
 * @param <T> - domain type
 */
public class NativeQueryWithResultSetMappingJpaCallback<T> implements JpaCallback<List<T>> {

    // native query sql
    private String nativeQuerySql;
    // sql result set mapping's name
    private String sqlResultSetMappingName;

    /**
     * Construct an instance of NativeQueryWithResultSetMappingJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql - native query sql
     * @param sqlResultSetMappingName -  sql result set mapping name
     */
    public NativeQueryWithResultSetMappingJpaCallback(String nativeQuerySql,
        String sqlResultSetMappingName) {
        this.nativeQuerySql = nativeQuerySql;
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
        return nativeQuery.getResultList();
    }

}
