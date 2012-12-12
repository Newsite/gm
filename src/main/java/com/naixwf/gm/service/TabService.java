/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service;

import java.util.List;

import com.naixwf.gm.domain.Tab;
import com.naixwf.gm.util.Page;

/**
 * 
 * @author wangfei
 * @created 2012-9-6
 * 
 * @version 1.0
 */
public interface TabService {

    /**
     * @author wangfei
     * @param tab
     */
    void insert(Tab tab);

    /**
     * @author wangfei
     * @param tabId
     * @return
     */
    Tab findById(Integer tabId);

    /**
     * 列出所有的tab
     * @author wangfei
     * @return
     */
    List<Tab> listAll(Page page);

    /**
     * 搜索tab
     * @author wangfei
     * @param type
     * @param keyword
     * @param page
     * @return
     */
    List<Tab> searchTab(Integer type, String keyword, Page page);

}
