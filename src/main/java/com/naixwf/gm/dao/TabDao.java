/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.dao;

import java.util.List;

import com.naixwf.core.dao.HibernateBaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.naixwf.gm.domain.Tab;
import com.naixwf.gm.util.Page;

/**
 * 
 * @author wangfei
 * @created 2012-9-6
 * 
 * @version 1.0
 */
@Repository
public class TabDao extends HibernateBaseDao<Tab> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabDao.class);

    /**
     * 分页列出所有的tab
     * 
     * @author wangfei
     * @param page
     * @return
     */
    public List<Tab> listAll(Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(Tab.class);
        return list(crit, page);
    }

    /**
     * 根据曲谱内容查找
     * 
     * @author wangfei
     * @param keyword
     * @return
     */
    public List<Tab> listByAllContent(String keyword, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(Tab.class);
        crit.add(Restrictions.or(
                Restrictions.like("content", "%" + keyword + "%"),
                Restrictions.or(Restrictions.like("singer", "%" + keyword + "%"),
                        Restrictions.like("name", "%" + keyword + "%"))));
        return list(crit, page);
    }

    /**
     * 根据歌手名查找
     * 
     * @author wangfei
     * @param keyword
     * @return
     */
    public List<Tab> listBySinger(String singer, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(Tab.class);
        crit.add(Restrictions.like("singer", "%" + singer + "%"));
        return list(crit, page);
    }

    /**
     * 根据曲名查找
     * 
     * @author wangfei
     * @param keyword
     * @return
     */
    public List<Tab> listBySongName(String name, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(Tab.class);
        crit.add(Restrictions.like("name", "%" + name + "%"));
        return list(crit, page);
    }

}
