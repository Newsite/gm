/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.util.StringUtil;

/**
 * 一个汉字
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public class Character {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Character.class);
    char hanZi;
    String[] pinYin;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hanZi);
        if (pinYin != null) {
            sb.append("(");
            for (int i = 0; i < pinYin.length; i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(pinYin[i]);
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public List<String> getPinYinList() {
        List<String> list = new ArrayList<String>();
        for (String item : pinYin) {
            list.add(item);
        }
        return list;
    }

    public Character(char hanZi, String[] pinYin) {
        this.hanZi = hanZi;
        this.pinYin = pinYin;
    }

    public char getHanZi() {
        return hanZi;
    }

    public void setHanZi(char hanZi) {
        this.hanZi = hanZi;
    }

    public String[] getPinYin() {
        return pinYin;
    }

    public void setPinYin(String[] pinYin) {
        this.pinYin = pinYin;
    }

}
