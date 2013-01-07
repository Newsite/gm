/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service;

import com.naixwf.gm.exam.Question;

/**
 * 试题相关的service
 * 
 * @author wangfei
 * @created 2012-12-27
 * 
 * @version 1.0
 */
public interface QuestionService {

    /**
     * 生成试题
     * @author wangfei
     * @param questionType 试题类型 refer QuestionType
     * @param param 试题所需参数
     * @return
     * @throws Throwable 
     */
    Question createQuestion(Integer questionType, String param) throws Throwable;

}
