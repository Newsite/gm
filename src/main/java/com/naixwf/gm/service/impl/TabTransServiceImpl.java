/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.naixwf.gm.domain.Jitapu;
import com.naixwf.gm.domain.Tab;
import com.naixwf.gm.service.TabService;
import com.naixwf.gm.service.TabTransService;

/**
 * 
 * @author wangfei
 * @created 2012-9-6
 * 
 * @version 1.0
 */
@Service
public class TabTransServiceImpl implements TabTransService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabTransServiceImpl.class);
    @Resource
    private TabService tabService;

    /**
     * @author wangfei
     * @param jitapu
     * @see com.naixwf.gm.service.TabTransService#insertJitapu(com.naixwf.gm.domain.Jitapu)
     */
    public String insertJitapu(Jitapu jitapu) {
        Tab tab = new Tab();
        String[] data = jitapu.getData().split(
                "-------------------------------------------------------------------------------");
        if (data.length != 3) {
            return ("横线分割后不等于3份:" + jitapu.getId() + ":" + data.length);
        }
        String info = data[1];
        String[] infoArr = info.split("\r\n");
        if (infoArr.length != 4) {
            return "曲谱信息不是3行";
        }
        String name = getValue(infoArr[1]);
        String singer = getValue(infoArr[2]);
        String source = getValue(infoArr[3]);

        tab.setName(name);
        tab.setSinger(singer);
        tab.setSource(source);
        data[2] = data[2].replace("吉他谱 http://www.jitapu.com", "");
        data[2] = data[2].replace("我们欢迎您转载曲谱，但请保留我们的地址和作者名！", "");
        tab.setContent(data[2]);
        tab.setSite("jitapu");
        tabService.insert(tab);
        return null;
    }

    /**
     * @author wangfei
     * @param string
     */
    private String getValue(String item) {
        Integer start = item.indexOf(":");
        if (start == -1) {
            start = item.indexOf("：");
        }
        item = item.substring(start + 1);
        return item.trim();
    }
}
