/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service.impl;

import java.util.ArrayList;
import java.util.List;

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
    public void insertJitapu(Jitapu jitapu) {
        Tab tab = new Tab();
        try {
            String[] data = jitapu
                    .getData()
                    .split("-------------------------------------------------------------------------------");
            String info = data[1];

            String[] infoArr = info.split("\\s");
            List<String> infoList = new ArrayList<String>();
            for (String item : infoArr) {
                if (!item.equals("")) {
                    Integer start = item.indexOf(":");
                    if (start == -1) {
                        start = item.indexOf("：");
                    }
                    item = item.substring(start + 1);
                    if (item.length() > 2) {
                        infoList.add(item);
                    }

                }
            }
            if (infoList.size() > 3) {
                logger.error(jitapu.getId() + ":" + infoList.toString());
            }
            tab.setName(infoList.get(0));
            tab.setSinger(infoList.get(1));
            tab.setSource(infoList.get(2));
            data[2] = data[2].replace("吉他谱 http://www.jitapu.com", "");
            data[2] = data[2].replace("我们欢迎您转载曲谱，但请保留我们的地址和作者名！", "");
            tab.setContent(data[2]);
            tab.setSite("jitapu");
            tabService.insert(tab);
        } catch (Exception e) {
            logger.debug("解析jitapu失败：id=" + jitapu.getId());
        }
    }
}
