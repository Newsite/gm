/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service;

import java.util.List;

import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.util.Page;

/**
 * 文本谱
 * 
 * @author wangfei
 * @created 2013-1-22
 * 
 * @version 1.0
 */
public interface TabTxtService {

    /**
     * @author wangfei
     * @param tabId
     * @return
     */
    TabTxt findById(Integer tabId);

    /**
     * 列出所有的tab
     * 
     * @author wangfei
     * @return
     */
    List<TabTxt> listAll(Page page);

    /**
     * 搜索tab
     * 
     * @author wangfei
     * @param type
     * @param keyword
     * @param page
     * @return
     */
    List<TabTxt> searchTab(Integer type, String keyword, Page page);

    /**
     * 列出最后i个文本谱
     * 
     * @author wangfei
     * @param i
     * @return
     */
    List<TabTxt> listLastTabs(int i);

    /**
     * 列出最火的i个文本谱
     * 
     * @author wangfei
     * @param i
     * @return
     */
    List<TabTxt> listHotTabs(int i);
}
