/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 * @author wangfei
 * @created 2012-9-4
 * 
 * @version 1.0
 */
public class BaseDao<T> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    @Resource(name = "gmHibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    protected HibernateTemplate hibernateTemplate;
    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        @SuppressWarnings("rawtypes")
        Class clazz = getClass();
        Type t = clazz.getGenericSuperclass();
        Type[] args = ((ParameterizedType) t).getActualTypeArguments();
        @SuppressWarnings("unused")
        Type type = args[0];
        this.clazz = (Class<T>) args[0];
    }

    /**
     * 
     * @author wangfei
     * @param criteria
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(DetachedCriteria criteria) {
        return hibernateTemplate.findByCriteria(criteria);
    }

    /**
     * 列出所有的记录
     * 
     * @author wangfei
     * @param criteria
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        return hibernateTemplate.findByCriteria(criteria);
    }

    /**
     * 根据主键查找
     * 
     * @author wangfei
     * @param id
     * @return
     */
    public T findById(Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    /**
     * 插入实体
     * 
     * @author wangfei
     * @param entity
     */
    public void insert(T entity) {
        hibernateTemplate.save(entity);
    }
}
