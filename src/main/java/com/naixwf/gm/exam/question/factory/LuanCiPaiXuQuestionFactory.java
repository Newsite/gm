/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam.question.factory;

import java.security.InvalidParameterException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.naixwf.gm.exam.Pinyin2HanziQuestion;
import com.naixwf.gm.exam.Question;
import com.naixwf.gm.exam.QuestionType;
import com.naixwf.gm.exam.question.body.LuanciPaixuBody;
import com.naixwf.gm.exam.question.body.QuestionBody;
import com.naixwf.gm.exam.question.body.StringSequnce;

/**
 * 乱词排序
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
@Component
public class LuanCiPaiXuQuestionFactory extends AbstractQuestionFactory {
    private static final Logger logger = LoggerFactory.getLogger(LuanCiPaiXuQuestionFactory.class);

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#getQuestionType()
     */
    @Override
    public int getQuestionType() {
        return QuestionType.LUAN_CI_PAI_JV;
    }

    /**
     * @author wangfei
     * @param param
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#checkParam(java.lang.String)
     */
    @Override
    protected QuestionBody checkParam(String param) {
        LuanciPaixuBody body = new LuanciPaixuBody();
        try {
            String[] subParams = param.split("\r\n");
            for (String subParam : subParams) {
                StringSequnce ss = new StringSequnce();
                String[] wordArray = subParam.split(" ");
                for (String word : wordArray) {
                    ss.add(word);
                }
                body.add(ss);
            }
            return body;
        } catch (Throwable e) {
            logger.error("", e);
            throw new InvalidParameterException(String.format("questionType=%d,param=%s",
                    getQuestionType(), param));
        }
    }

    /**
     * 
     * @author wangfei
     * @param param
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#generateQuestion(com.naixwf.gm.exam.question.body.QuestionBody)
     */
    @Override
    protected Question generateQuestion(QuestionBody body) {
        LuanciPaixuBody lcpxBody = (LuanciPaixuBody) body;
        for(StringSequnce s:lcpxBody){
            Collections.shuffle(s);
        }
        Question question = new Pinyin2HanziQuestion();
        question.setBody(lcpxBody);
        return question;
    }
}
