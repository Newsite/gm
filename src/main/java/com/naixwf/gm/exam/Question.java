/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam;

import com.naixwf.gm.exam.question.body.QuestionBody;

/**
 * 试题
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public interface Question {
    /**
     * 获取封装好的参数
     * 
     * @author wangfei
     * @return
     */
    QuestionBody getBody();

    /**
     * 设置参数
     * 
     * @author wangfei
     * @param param
     */
    void setBody(QuestionBody body);
}
