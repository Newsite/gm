/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam.question.factory;

import com.naixwf.gm.exam.Question;

/**
 * 试题工厂
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public interface QuestionFactory {

    /**
     * 根据字符串生成试题
     * 
     * @author wangfei
     * @param param
     * @throws Throwable
     */
    Question createQuestion(String param) throws Throwable;

    /**
     * 试题类型
     * 
     * @author wangfei
     * @return
     */
    int getQuestionType();
}
