/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.naixwf.gm.dao.JitapuDao;
import com.naixwf.gm.service.TabTransService;

/**
 * 
 * @author wangfei
 * @created 2012-6-5
 * 
 * @version 1.0
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    JitapuDao jitapuDao;
    @Resource
    TabTransService tabTransService;
    @Resource
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * 首页
     * 
     * @author wangfei
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Integer tabId, Model model) {
        return "redirect:tab/list";
    }

    /**
     * 把jitapu表转化进tab表
     * 
     * @author wangfei
     */
    @RequestMapping("/transe_jitapu")
    public @ResponseBody
    Map<String, Object> saveJitapu() {
        // List<String> errorList = new ArrayList<String>();
        // List<Jitapu> list = jitapuDao.listAll();
        // for (Jitapu item : list) {
        // String error = tabTransService.insertJitapu(item);
        // if (error != null) {
        // errorList.add(error);
        // }
        // }
        // logger.warn(String.format("共%d条,失败%d条", list.size(),
        // errorList.size()));
        // for (String item : errorList) {
        // logger.warn(item);
        // }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "转换完毕");
        return map;
    }
}
