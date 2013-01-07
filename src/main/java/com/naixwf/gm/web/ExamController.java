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
import org.springframework.web.bind.annotation.RequestMethod;

import com.naixwf.gm.exam.Question;
import com.naixwf.gm.service.QuestionService;

/**
 * @author wangfei
 * @created 2012-12-24
 * 
 * @version 1.0
 */
@RequestMapping("/exam")
@Controller
public class ExamController {
    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);
    @Resource
    private QuestionService questionService;

    @RequestMapping("/index")
    public String index() {
        return "/exam/index";
    }

    @RequestMapping(value = "/generate_question")
    public String test(String param, Integer questionType, Model model) throws Throwable {
        Question question = questionService.createQuestion(questionType, param);
        model.addAttribute("question", question);
        model.addAttribute("param", param);
        model.addAttribute("questionType", questionType);
        return "/exam/generate_question";
    }
}
