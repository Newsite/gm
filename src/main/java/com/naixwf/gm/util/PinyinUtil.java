/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.exam.Character;
import com.naixwf.gm.exam.Word;

/**
 * 中文转拼音
 * 
 * @author wangfei
 * @version 1.0
 * @created 2012-12-24
 */
public class PinyinUtil {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(PinyinUtil.class);

    public static Word getWord(String zhongwen) throws BadHanyuPinyinOutputFormatCombination {
        List<Character> charList = new ArrayList<Character>();
        char[] chars = zhongwen.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            String[] pinYin = PinyinHelper
                    .toHanyuPinyinStringArray(chars[i], getDefaultFormatter());
            Character c = new Character(chars[i], pinYin);
            charList.add(c);

        }
        return new Word(charList);
    }

    /**
     * @author wangfei
     * @param pinYin
     * @param string
     * @return
     */
    private static String stringArrJoin(String[] array, String division) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (String s : array) {
            if (flag) {// 不是第一个
                sb.append(division);
            }
            sb.append(s);
            flag = true;
        }
        return sb.toString();
    }

    /**
     * 默认的拼音格式
     * 
     * @return
     * @author wangfei
     */
    private static HanyuPinyinOutputFormat getDefaultFormatter() {
        HanyuPinyinOutputFormat formater = new HanyuPinyinOutputFormat();
        formater.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        formater.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        formater.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        return formater;
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(getWord("有一个重要的事情要和你说").toString());
    }
}
