/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wangfei
 * @created 2012-6-5
 *
 * @version 1.0
 */
@Controller
public class HomeController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * 首页
     * @author wangfei
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model){
        logger.debug("进入homeController处理");
        model.addAttribute("content","hello");
        return "index";
    }
}
