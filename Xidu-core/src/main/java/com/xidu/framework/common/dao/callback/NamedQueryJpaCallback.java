/************************************************************************************
 * @File name   :      NamedQueryJpaCallback.java
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
 * 2011-3-9 上午10:30:06         Jianxi Wu           1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

/**
 *NamedQuery's JpaCallback's implementation.
 * 
 * @param <T>
 *            indicate domain type
 */
public class NamedQueryJpaCallback<T> implements JpaCallback<List<T>> {

    // NamedQuery's or NamedNativeQuery's name
    private String queryName;

    /**
     * construct an instance of NamedQueryJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - NamedQuery's or NamedNativeQuery's name
     */
    public NamedQueryJpaCallback(String queryName) {
        this.queryName = queryName;
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
        return namedQuery.getResultList();
    }

}
