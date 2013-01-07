/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam.question.body;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.exam.Word;

/**
 * 词组序列
 * 
 * @author wangfei
 * @created 2012-12-27
 * 
 * @version 1.0
 */
public class WordSequnce extends ArrayList<Word> implements SubQuestionBody {
    private static final long serialVersionUID = -4930613083588983157L;
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WordSequnce.class);
}
