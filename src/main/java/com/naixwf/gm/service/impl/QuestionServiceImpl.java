/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service.impl;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.naixwf.gm.exam.Question;
import com.naixwf.gm.exam.question.factory.QuestionFactory;
import com.naixwf.gm.service.QuestionService;

/**
 * @author wangfei
 * @created 2012-12-27
 * 
 * @version 1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Resource
    private ApplicationContext applicationContext;
    private Map<Integer, QuestionFactory> questionFactoryMap = new HashMap<Integer, QuestionFactory>();

    @PostConstruct
    public void init() {
        questionFactoryMap = new HashMap<Integer, QuestionFactory>();
        Map<String, QuestionFactory> tmps = applicationContext
                .getBeansOfType(QuestionFactory.class);
        for (String name : tmps.keySet()) {
            QuestionFactory qf = tmps.get(name);
            int questionTypeId = qf.getQuestionType();
            logger.debug("初始化试题工厂:" + qf.getQuestionType());
            if (questionFactoryMap.containsKey(questionTypeId)) {
                throw new IllegalStateException("重复的试题工厂类型：typeId=" + questionTypeId + ",classes="
                        + qf + "," + questionFactoryMap.get(questionTypeId));
            } else {
                questionFactoryMap.put(questionTypeId, qf);
            }
        }
    }

    /**
     * @author wangfei
     * @param questionType
     * @param param
     * @return
     * @throws Throwable
     * @see com.naixwf.gm.service.QuestionService#createQuestion(java.lang.Integer,
     *      java.lang.String)
     */
    public Question createQuestion(Integer questionType, String param) throws Throwable {
        if (questionType == null) {
            throw new InvalidParameterException("questionType不能为空");
        }
        QuestionFactory qf = questionFactoryMap.get(questionType);
        Question q = qf.createQuestion(param);
        return q;
    }
}
