/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.naixwf.gm.util.Page;

/**
 * @author wangfei
 * @version 1.0
 * @created 2012-9-4
 */
public class HibernateBaseDao<T> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HibernateBaseDao.class);

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    protected HibernateTemplate hibernateTemplate;
    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public HibernateBaseDao() {
        @SuppressWarnings("rawtypes")
        Class clazz = getClass();
        Type t = clazz.getGenericSuperclass();
        Type[] args = ((ParameterizedType) t).getActualTypeArguments();
        @SuppressWarnings("unused")
        Type type = args[0];
        this.clazz = (Class<T>) args[0];
    }

    /**
     * @param criteria
     * @return
     * @author wangfei
     */
    @SuppressWarnings("unchecked")
    protected List<T> list(DetachedCriteria criteria) {
        return hibernateTemplate.findByCriteria(criteria);
    }

    /**
     * 列出所有的记录
     *
     * @return
     * @author wangfei
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        return hibernateTemplate.findByCriteria(criteria);
    }

    /**
     * 根据主键查找
     *
     * @param id
     * @return
     * @author wangfei
     */
    public T findById(Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    /**
     * 插入实体
     *
     * @param entity
     * @author wangfei
     */
    public void insert(T entity) {
        hibernateTemplate.save(entity);
    }

    public void insert(Collection<T> c) {
        for (T t : c) {
            insert(t);
        }
    }

    /**
     * 根据条件分页查找
     *
     * @param crit
     * @param page
     * @return
     * @author wangfei
     */
    public List<T> list(final DetachedCriteria crit, Page page) {
        if (page == null) {
            list(crit);
        }
        int recordCount = countByCrit(crit);
        page.setTotalCount(recordCount);
        return list(crit, page.getStart(), page.getPageSize());
    }

    /**
     * 查询某一页的数据
     *
     * @param crit
     * @return
     * @author wangfei
     */
    @SuppressWarnings("unchecked")
    private List<T> list(final DetachedCriteria crit, final int offset, final int length) {
        crit.setProjection(Projections.rowCount());
        List<T> list = hibernateTemplate.executeFind(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = crit.getExecutableCriteria(session);
                c.setProjection(null);
                c.setFirstResult(offset);
                c.setMaxResults(length);
                return (List<T>) c.list();
            }
        });
        return list;
    }

    /**
     * 根据条件查找条数
     *
     * @param crit
     * @return
     * @author wangfei
     */
    private int countByCrit(final DetachedCriteria crit) {
        @SuppressWarnings("unchecked")
        List<T> list = hibernateTemplate.executeFind(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = crit.getExecutableCriteria(session);
                c.setProjection(Projections.rowCount());
                return (List<T>) c.list();
            }
        });
        return ((Long) list.get(0)).intValue();
    }


}
