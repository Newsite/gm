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

import com.naixwf.gm.dao.TabDao;
import com.naixwf.gm.domain.Tab;
import com.naixwf.gm.service.TabService;

/**
 * 
 * @author wangfei
 * @created 2012-9-6
 * 
 * @version 1.0
 */
@Service
public class TabServiceImpl implements TabService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabServiceImpl.class);
    @Resource
    private TabDao tabDao;

    /**
     * @author wangfei
     * @param tab
     * @see com.naixwf.gm.service.TabService#insert(com.naixwf.gm.domain.Tab)
     */
    public void insert(Tab tab) {
        tabDao.insert(tab);
    }

    /**
     * @author wangfei
     * @param tabId
     * @return
     * @see com.naixwf.gm.service.TabService#findById(java.lang.Integer)
     */
    public Tab findById(Integer tabId) {
        return tabDao.findById(tabId);
    }

    /**
     * @author wangfei
     * @param list
     * @return
     * @see com.naixwf.gm.service.TabService#listAll(java.util.List)
     */
    public List<Tab> listAll() {
        return tabDao.listAll();
    }
}
