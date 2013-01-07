/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam.question.factory;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.exam.Question;
import com.naixwf.gm.exam.question.body.QuestionBody;

/**
 * 抽象试题厂
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public abstract class AbstractQuestionFactory implements QuestionFactory {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AbstractQuestionFactory.class);

    /**
     * 试题类型
     * 
     * @author wangfei
     * @return
     */
    public abstract int getQuestionType();

    /**
     * 根据参数生成试题
     * 
     * @author wangfei
     * @param param
     * @return
     * @throws Throwable
     * @see com.naixwf.gm.exam.question.factory.QuestionFactory#createQuestion(java.lang.String)
     */
    public Question createQuestion(String param) throws Throwable {
        QuestionBody questionParam = checkParam(param);
        if (questionParam != null) {
            return generateQuestion(questionParam);
        } else {
            throw new InvalidParameterException(String.format("questionType=%d的试题不接受此参数:%s",
                    getQuestionType(), param));
        }
    }

    /**
     * 检查参数
     * 
     * @author wangfei
     * @param param
     * @return
     */
    protected abstract QuestionBody checkParam(String param);

    /**
     * 使用param生成试题,已确保param可用
     * 
     * @author wangfei
     * @param param
     * @return
     */
    protected abstract Question generateQuestion(QuestionBody param);
}
