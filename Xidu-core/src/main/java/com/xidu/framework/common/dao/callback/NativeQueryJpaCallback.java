/************************************************************************************
 * @File name   :      NativeQueryJpaCallback.java
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
 * 2011-3-9 上午10:11:20         Jianxi Wu           1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

/**
 *JpaCallback's implementation of JPA's NativeQuery.
 * 
 * @param <T>
 *            - JP-Query string
 */
public class NativeQueryJpaCallback<T> implements JpaCallback<List<T>> {

    // native query sql
    private String nativeQuerySql;
    // domain Class instance
    private Class<T> clazz;

    /**
     * construct an instance of NativeQueryJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql
     * @param clazz
     *            - domain Class instance
     */
    public NativeQueryJpaCallback(String nativeQuerySql, Class<T> clazz) {
        this.nativeQuerySql = nativeQuerySql;
        this.clazz = clazz;
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
        Query nativeQuery = em.createNativeQuery(nativeQuerySql, clazz);
        return nativeQuery.getResultList();
    }

}
