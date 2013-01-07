/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.exam.question.body.QuestionBody;

/**
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public class AbstractQuestion implements Question {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AbstractQuestion.class);
    protected QuestionBody body;

    public QuestionBody getBody() {
        return body;
    }

    public void setBody(QuestionBody body) {
        this.body = body;
    }

}
