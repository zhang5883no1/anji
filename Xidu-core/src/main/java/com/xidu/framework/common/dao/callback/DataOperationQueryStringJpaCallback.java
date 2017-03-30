/************************************************************************************
 * @File name   :      QueryUpdateStringJpaCallback.java
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
 * Date                         Who                 Version         Comments
 * 2011-3-4 下午03:25:48        Jianxi Wu            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.callback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

/**
 *JP-Query JpaCallback's implementation.
 */
public class DataOperationQueryStringJpaCallback implements JpaCallback<Integer> {
    // data operation query string,like update JP-Query,delete JP-Query
    private String updateQueryStr;

    /**
     * construct an instance of DataOperationQueryStringJpaCallback.
     * 
     * @Date : 2011-3-23
     * @param updateQueryStr
     *            - JP-Query string
     */
    public DataOperationQueryStringJpaCallback(String updateQueryStr) {
        this.updateQueryStr = updateQueryStr;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see org.springframework.orm.jpa.JpaCallback#doInJpa(javax.persistence.EntityManager)
     * 
     */
    @Override
    public Integer doInJpa(EntityManager em) throws PersistenceException {
        Query query = em.createQuery(updateQueryStr);
        return query.executeUpdate();
    }

}
