/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.service.TabTxtService;

/**
 * 
 * @author wangfei
 * @created 2012-6-5
 * 
 * @version 1.0
 */
@Controller
public class IndexController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private TabTxtService tabTxtService;

    @RequestMapping("/")
    public String home(Integer tabId, Model model) {
        return "redirect:index";
    }

    /**
     * 首页
     * 
     * @author wangfei
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Integer tabId, Model model) {
        List<TabTxt> lastTabList = tabTxtService.listLastTabs(10);
        List<TabTxt> hotTabList = tabTxtService.listHotTabs(10);
        model.addAttribute("lastTabList", lastTabList);
        model.addAttribute("hotTabList", hotTabList);
        return "index";
    }
}
