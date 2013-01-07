/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam.question.factory;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.naixwf.gm.exam.Pinyin2HanziQuestion;
import com.naixwf.gm.exam.Question;
import com.naixwf.gm.exam.QuestionType;
import com.naixwf.gm.exam.Word;
import com.naixwf.gm.exam.question.body.QuestionBody;
import com.naixwf.gm.exam.question.body.WordSequnce;
import com.naixwf.gm.exam.question.body.WriteHanziByPinyinBody;
import com.naixwf.gm.util.PinyinUtil;

/**
 * 根据拼音写汉字
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
@Component
public class Pinyin2HanziQuestionFactory extends AbstractQuestionFactory {
    private static final Logger logger = LoggerFactory.getLogger(Pinyin2HanziQuestionFactory.class);

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#getQuestionType()
     */
    @Override
    public int getQuestionType() {
        return QuestionType.GEN_JV_PIN_YIN_XIE_HAN_ZI;
    }

    /**
     * @author wangfei
     * @param param
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#checkParam(java.lang.String)
     */
    @Override
    protected QuestionBody checkParam(String param) {
        logger.info("param:" + param);
        WriteHanziByPinyinBody list = new WriteHanziByPinyinBody();
        try {
            String[] subParams = param.split("\r\n");
            for (String subParam : subParams) {
                WordSequnce ws = new WordSequnce();
                String[] wordArray = subParam.split(" ");
                for (String word : wordArray) {
                    Word w = PinyinUtil.getWord(word);
                    ws.add(w);
                }
                list.add(ws);
            }
            return list;
        } catch (Throwable e) {
            logger.error("", e);
            throw new InvalidParameterException(String.format("questionType=%d,param=%s",
                    getQuestionType(), param));
        }
    }

    /**
     * @author wangfei
     * @param param
     * @return
     * @see com.naixwf.gm.exam.question.factory.AbstractQuestionFactory#generateQuestion(com.naixwf.gm.exam.question.body.QuestionBody)
     */
    @Override
    protected Question generateQuestion(QuestionBody body) {
        Question question = new Pinyin2HanziQuestion();
        question.setBody(body);
        return question;
    }
}
