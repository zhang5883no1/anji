/************************************************************************************
 * @File name   :      QueryJpaCallback.java
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
 * 2011-3-9 上午09:01:04         Jianxi Wu            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

/**
 *JapCalback's implementation of JPA's Query.
 * 
 * @param <T>
 *            - domain type
 */
public class QueryJpaCallback<T> implements JpaCallback<List<T>> {

    // JPA-Query string
    private String queryStr;

    /**
     * Construct an instance of QueryJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JPA-Query string
     */
    public QueryJpaCallback(String queryStr) {
        this.queryStr = queryStr;
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

        return query.getResultList();
    }

}
