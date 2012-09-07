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

import com.naixwf.gm.domain.Tab;
import com.naixwf.gm.service.TabService;

/**
 * 具体谱子相关
 * 
 * @author wangfei
 * @created 2012-9-5
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/tab")
public class TabController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabController.class);
    @Resource
    private TabService tabService;

    @RequestMapping("/details")
    public String details(Integer tabId, Model model) {
        Tab tab = tabService.findById(tabId);
        model.addAttribute("tab", tab);
        return "tab/details";
    }

    /**
     * 曲谱列表
     * 
     * @author wangfei
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<Tab> list = tabService.listAll();
        model.addAttribute("list", list);
        return "tab/list";
    }

}
