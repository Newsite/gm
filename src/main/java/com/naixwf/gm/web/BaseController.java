/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.naixwf.chord4j.chord.dic.Pitch;
import com.naixwf.gm.constant.SearchTypeDef;

/**
 * 向model中放置搜索条件
 * 
 * @author wangfei
 * @created 2013-1-9
 * 
 * @version 1.0
 */
public class BaseController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected void putSearchType2Model(Model model) {
        Map<String, String> searchTypeMap = new TreeMap<String, String>();
        searchTypeMap.put(SearchTypeDef.ALL + "", "全部");
        searchTypeMap.put(SearchTypeDef.SINGER + "", "歌手");
        searchTypeMap.put(SearchTypeDef.SONG + "", "曲名");
        model.addAttribute("searchTypeMap", searchTypeMap);
    }

    /**
     * 调音器
     * 
     * @author wangfei
     * @param keyChosen
     */
    protected void putTransposer(String keyStart, Model model) {
        List<String> keyList = new ArrayList<String>();
        Pitch p = new Pitch(keyStart);
        for (int i = 0; i < 12; i++) {
            keyList.add(p.getName());
            p = p.add(1);
        }
        model.addAttribute("keyList", keyList);
    }
}
