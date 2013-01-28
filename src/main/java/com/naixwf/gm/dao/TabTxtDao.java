/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.util.Page;

/**
 * 
 * @author wangfei
 * @created 2013-1-22
 * 
 * @version 1.0
 */
@Repository
public class TabTxtDao extends BaseDao<TabTxt> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabTxtDao.class);

    /**
     * 分页列出所有的tab
     * 
     * @author wangfei
     * @param page
     * @return
     */
    public List<TabTxt> listAll(Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(TabTxt.class);
        return list(crit, page);
    }

    /**
     * 根据曲谱内容查找
     * 
     * @author wangfei
     * @param keyword
     * @return
     */
    public List<TabTxt> listByAllContent(String keyword, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(TabTxt.class);
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
    public List<TabTxt> listBySinger(String singer, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(TabTxt.class);
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
    public List<TabTxt> listBySongName(String name, Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(TabTxt.class);
        crit.add(Restrictions.like("name", "%" + name + "%"));
        return list(crit, page);
    }

    /**
     * 按添加时间倒序排列
     * 
     * @author wangfei
     * @param page
     * @return
     */
    public List<TabTxt> listByAddTimeDesc(Page page) {
        DetachedCriteria crit = DetachedCriteria.forClass(TabTxt.class);
        crit.addOrder(Order.desc("addTime"));
        return list(crit, page);
    }
}
