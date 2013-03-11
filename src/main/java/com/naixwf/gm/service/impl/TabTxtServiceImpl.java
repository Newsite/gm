/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.naixwf.gm.constant.SearchTypeDef;
import com.naixwf.gm.dao.TabTxtDao;
import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.service.TabTxtService;
import com.naixwf.gm.util.Page;

/**
 * 
 * @author wangfei
 * @created 2013-1-22
 * 
 * @version 1.0
 */
@Service
public class TabTxtServiceImpl implements TabTxtService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabTxtServiceImpl.class);
    @Resource
    private TabTxtDao tabTxtDao;

    /**
     * @author wangfei
     * @param tabId
     * @return
     * @see com.naixwf.gm.service.TabTxtService#findById(java.lang.Integer)
     */
    public TabTxt findById(Integer tabId) {
        return tabTxtDao.findById(tabId);
    }

    public List<TabTxt> listAll(Page page) {
        if (page == null) {
            page = new Page();
        }
        return tabTxtDao.listAll(page);
    }

    public List<TabTxt> searchTab(Integer type, String keyword, Page page) {
        if (type == null) {
            type = SearchTypeDef.ALL;
        }
        if (keyword == null) {
            return tabTxtDao.listAll(page);
        }
        if (page == null) {
            page = new Page();
        }
        List<TabTxt> list = null;
        switch (type) {
        case SearchTypeDef.ALL:
            list = tabTxtDao.listByAllContent(keyword, page);
            break;
        case SearchTypeDef.SINGER:
            list = tabTxtDao.listBySinger(keyword, page);
            break;
        case SearchTypeDef.SONG:
            list = tabTxtDao.listBySongName(keyword, page);
            break;
        default:
            list = tabTxtDao.listAll(page);
        }
        return list;
    }

    /**
     * @author wangfei
     * @param i
     * @return
     * @see com.naixwf.gm.service.TabTxtService#listLastTabs(int)
     */
    public List<TabTxt> listLastTabs(int i) {
        Page page = new Page(1, i);
        return tabTxtDao.listByAddTimeDesc(page);
    }

    /**
     * TODO
     * 
     * @author wangfei
     * @param i
     * @return
     * @see com.naixwf.gm.service.TabTxtService#listHotTabs(int)
     */
    public List<TabTxt> listHotTabs(int i) {
        return listLastTabs(i);
    }
}
