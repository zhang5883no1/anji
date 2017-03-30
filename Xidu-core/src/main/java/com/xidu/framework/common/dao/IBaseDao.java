/************************************************************************************
 * @File name   :      BaseDao.java
 *
 * @Author      :      Brenda Yin
 *
 * @Date        :      2011-2-16
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who                 Version             Comments
 * 2011-1-26 上午10:15:14         Brenda Yin            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.xidu.framework.common.util.Pager;

/**
 * A Base DAO Interface. Define some common data operation methods(like
 * update,save,delete,query).It provides a middle layer between dao and JPA API.
 * 
 * 
 * @param <T>-indicates the Type of DAO, ex: domain object,like User.
 * @param <PK>-indicates the Type of PK, ex: Long
 */
public interface IBaseDao<T, PK> {

    /**
     * get current EntityManager's instance.
     * 
     * @Date : 2011-3-23
     * @return instance of EntityManager
     */
    EntityManager getEntityManager();

    /**
     * call JpaTemplate.flush() to flush EntityManager.
     * 
     * @Date : 2011-3-23
     */
    void flush();

    /**
     * clear EntityManager.
     * 
     * @Date : 2011-3-23
     */
    void clear();

    /**
     * get specific domain by primary id.
     * 
     * @Date : 2011-3-23
     * @param id
     *            - primary id of domain
     * @return <T> - specific domain
     */
    T findById(PK id);
    
    /**
     * 
     * @Date        :      2013-5-31
     * @return
     */
    List<T> findAll();
    
    /**
     * persistent specific domain object to db.
     * 
     * @Date : 2011-3-23
     * @param obj
     *            - the specific domain
     * @return the specific domain including primary id.
     */
    T save(T obj);

    /**
     * update specific domain object.
     * 
     * @Date : 2011-3-23
     * @param obj
     *            - the domain object
     */
    void update(T obj);

    /**
     * update by update sql.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - update JP-Query string
     */
    void updateByQuery(String queryStr);

    /**
     * update domain by hql and parameters.
     * @Date        :      2011-4-1
     * @param queryStr - update JP-Query string
     * @param paraMap - parameter map
     */
    void updateByQueryAndMap(final String queryStr, final Map<String,?> paraMap);
    
    /**
     * update domain by hql and variable parameters
     * @Date        :      2011-4-1
     * @param queryStr - update JP-Query string
     * @param params - parameter map
     */
    void updateByQUeryAndVaParam(final String queryStr, final Object... params);
    /**
     * remove specific domain object including primary id.
     * 
     * @Date : 2011-3-23
     * @param obj
     *            - the domain object
     */
    void remove(T obj);

    /**
     * delete sth by delete sql.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - the delete JP-Query string
     */
    void removeByQuery(String queryStr);

    /**
     * delete domain by hql and parameters.
     * @Date        :      2011-4-1
     * @param queryStr - update JP-Query string
     * @param paraMap - parameter map
     */
    void removeByQueryAndMap(final String queryStr, final Map<String,?> paraMap);
    
    /**
     * delete domain by hql and variable parameters
     * @Date        :      2011-4-1
     * @param queryStr - update JP-Query string
     * @param params - parameter map
     */
    void removeByQueryAndVaParam(final String queryStr, final Object... params);
    /**
     * delete domain object by primary id.
     * 
     * @Date : 2011-3-23
     * @param id
     *            - the primary id
     */
    void remove(long id);

    /**
     * query domain list by JP-Query string.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query String
     * @return the List of domain Object.
     */
    List<T> getListByQuery(final String queryStr);

    /**
     * query domain list with paging functionality.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param countQueryStr
     *            - count JP-Query string
     * @param pager
     *            - pager instance
     * @return the List of domain object.
     */
    List<T> getListByQueryWithPaging(final String queryStr,
            final String countQueryStr, Pager pager);

    /**
     * query domain list with default paging count JP-Query string.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param pager
     *            - pager instance.
     * @return the List of domain object.
     */
    List<T> getListByQueryWithDefaultPaging(final String queryStr, Pager pager);
    
    /**
     * query domain list distinct by distinct Column with default paging count JP-Query string.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param pager
     *            - pager instance.
     * @param distinct Column
     * @return the List of domain object.
     */
    List<T> getListByQueryWithDefaultPagingDistinct(final String queryStr,final String distinctColumn,Pager pager);

    /**
     * query domain list by JP-Query string and map of query parameters.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param paraMap
     *            - map of query parameters.
     * @return list of domain object
     */
    List<T> getListByQueryWithMap(final String queryStr,
            final Map<String, ?> paraMap);

    /**
     * query domain list by JP-Query string and variable parameters.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - JP-Query string
     * @param params
     *            - variable parameters set to indexed position in JP-Query.
     * @return the List of domain object.
     */
    List<T> getListByQueryWithVaParam(final String queryStr,
            final Object... params);

    /**
     * count record size by count JP-Query string.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - count JP-Query string
     * @return the size of records.
     */
    int countByQuery(final String queryStr);

    /**
     * count record size by count JP-Query string and query parameters' map.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - count JP-Query string.
     * @param paraMap
     *            - map of query parameters.
     * @return the size of records.
     */
    int countByQueryWithMap(final String queryStr, final Map<String, ?> paraMap);

    /**
     * count record size by count JP-Query string and variable query parameters.
     * 
     * @Date : 2011-3-23
     * @param queryStr
     *            - count JP-Query string.
     * @param params
     *            - variable parameters.
     * @return the size of records.
     */
    int countByQueryWithVaParam(final String queryStr, final Object... params);

    /**
     * query domain list by JPA's NamedQuery's or NamedNativeQuery's name.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery and NamedNativeQuery
     * @return the List of domain object.
     */
    List<T> getListByNamedQuery(final String queryName);

    /**
     * query doamin list with paging funtionality.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery and NamedNativeQuery
     * @param countQueryName
     *            the count query name of NamedQuery and NamedNativeQuery
     * @param pager
     *            - instance of Pager.
     * @return the List of domain object.
     */
    List<T> getListByNamedQueryWithPaging(final String queryName,
            final String countQueryName, Pager pager);

    /**
     * query domain list by NamedQuery's or NamedNativeQuery's name and map of
     * query parameters.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery or NamedNativeQuery.
     * @param paraMap
     *            - map of query parameters.
     * @return the List of domain object.
     */
    List<T> getListByNamedQueryWithMap(final String queryName,
            final Map<String, ?> paraMap);

    /**
     * query domain list by NamedQuery's or NamedNativeQuery's name and variable
     * query parameters.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery or NamedNativeQuery.
     * @param params
     *            - map of query parameters.
     * @return the List of domain object.
     */
    List<T> getListByNamedQueryWithVaParam(final String queryName,
            final Object... params);

    /**
     * count record size by NamedQuery's or NamedNativeQuery's name.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery or NamedNativeQuery.
     * @return the size of records.
     */
    int countByNamedQuery(final String queryName);

    /**
     * count record size by NamedQuery's or NamedNativeQuery's name and its
     * parameters' map.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery or NamedNativeQuery.
     * @param paraMap
     *            - map of query parameters.
     * @return the size of records.
     */
    int countByNamedQueryWithMap(final String queryName,
            final Map<String, ?> paraMap);

    /**
     * count record size by NamedQuery's or NamedNativeQuery's name and variable
     * query parameters.
     * 
     * @Date : 2011-3-23
     * @param queryName
     *            - the query name of NamedQuery or NamedNativeQuery.
     * @param params
     *            - map of query parameters.
     * @return the size of records
     */
    int countByNamedQueryWithVaParam(final String queryName,
            final Object... params);

    /**
     * query domain list by native query sql.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @return the List of domain object.
     */
    List<T> getListByNativeQuery(final String nativeQuerySql);

    /**
     * query domain list with paging funtionality.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param countNativeQuerySql
     *            - count native query sql.
     * @param pager
     *            - instance of Pager.
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithPaging(final String nativeQuerySql,
            final String countNativeQuerySql, Pager pager);

    /**
     * query domain list by native query sql and map of query parameters.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param paraMap
     *            - map of query parameters.
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithMap(final String nativeQuerySql,
            final Map<String, ?> paraMap);

    /**
     * query domain list by native query sql and variable query parameters.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param params
     *            - variable query parameters.
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithVaParam(final String nativeQuerySql,
            final Object... params);

    /**
     * query domain list by native query sql and sql result set mapping's name.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param sqlResultSetMappingName
     *            - sql result set mapping's name.
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryAndRSMapping(final String nativeQuerySql,
            final String sqlResultSetMappingName);

    /**
     * query domain list with paging funtionality.
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param sqlResultSetMappingName
     *            - sql result set mapping's name.
     * @param countNativeQuerySql
     *            - native count sql.
     * @param pager
     *            - instance of Pager
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithPagingAndRSMapping(
            final String nativeQuerySql, final String sqlResultSetMappingName,
            final String countNativeQuerySql, Pager pager);

    /**
     * query domain list by native query sql, sql result set mapping's name and
     * query parameters's map
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param sqlResultSetMappingName
     *            - sql result set mapping's name
     * @param paraMap
     *            - map of query parameters.
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithMapAndRSMapping(
            final String nativeQuerySql, final String sqlResultSetMappingName,
            final Map<String, ?> paraMap);

    /**
     * query domain list by native query sql, sql result set mapping's name and
     * variable query parameters
     * 
     * @Date : 2011-3-23
     * @param nativeQuerySql
     *            - native query sql.
     * @param sqlResultSetMappingName
     *            - sql result set mapping's name
     * @param params
     *            - variable query parameters
     * @return the List of domain object.
     */
    List<T> getListByNativeQueryWithVaParamAndRSMapping(
            final String nativeQuerySql, final String sqlResultSetMappingName,
            final Object... params);

    /**
     * count record size by native count sql.
     * @Date        :      2011-3-23
     * @param nativeQuerySql - native count sql
     * @return the size of records.
     */
    int countByNativeQuery(final String nativeQuerySql);

    /**
     * count record size by native count sql and map of query parameters.
     * @Date        :      2011-3-23
     * @param nativeQuerySql - native count sql
     * @param paraMap - map of query parameters.
     * @return the size of records.
     */
    int countByNativeQueryWithMap(final String nativeQuerySql,
            final Map<String, ?> paraMap);

    /**
     * count record size by native count sql and variable query parameters.
     * @Date        :      2011-3-23
     * @param nativeQuerySql - native count sql
     * @param params - variable parameters
     * @return - the size of records
     */
    int countByNativeQueryWithVaParam(final String nativeQuerySql,
            final Object... params);
    

}
