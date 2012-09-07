/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
