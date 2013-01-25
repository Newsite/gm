/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web.vo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangfei
 * @created 2013-1-9
 * 
 * @version 1.0
 */
public class Sentence {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Sentence.class);
    // 和弦
    List<String> chordList;
    // 歌词
    String lyric;

    /**
     * @param asList
     * @param string
     */
    public Sentence(List<String> chordList, String lyric) {
        this.chordList = chordList;
        this.lyric = lyric;
    }

    public List<String> getChordList() {
        return chordList;
    }

    public void setChordList(List<String> chordList) {
        this.chordList = chordList;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

}
