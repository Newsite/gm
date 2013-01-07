/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exam;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 词语
 * 
 * @author wangfei
 * @created 2012-12-25
 * 
 * @version 1.0
 */
public class Word {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Word.class);
    private List<Character> characters;

    @Override
    public String toString() {
        if (characters == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < characters.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(characters.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * 返回汉字字符串
     * 
     * @author wangfei
     * @return
     */
    public String getHanziString() {
        if (characters == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < characters.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(characters.get(i).hanZi);
        }
        return sb.toString();
    }

    public Word(List<Character> charList) {
        this.characters = charList;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}
