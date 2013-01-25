/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.dic.Pitch;
import com.naixwf.chord4j.chord.dic.ToneMarker;
import com.naixwf.gm.util.TabUtil;

/**
 * 文本谱内容vo
 * 
 * @author wangfei
 * @created 2013-1-9
 * 
 * @version 1.0
 */
public class TabContentVo extends ArrayList<Sentence> {
    private static final long serialVersionUID = 3031542155409114325L;
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabContentVo.class);

    /**
     * 转调
     * 
     * @author wangfei
     * @param from
     * @param to
     */
    public TabContentVo transpose(String from, String to) {
        Pitch p1 = new Pitch(from);
        Pitch p2 = new Pitch(to);
        int offset = p2.getValue() - p1.getValue();
        return transpose(offset);
    }

    /**
     * 转调
     * 
     * @author wangfei
     * @param offset
     *            升多少
     * @return
     */

    public TabContentVo transpose(int offset) {
        for (Sentence s : this) {
            List<String> chordList = s.getChordList();
            for (int i = 0, size = chordList.size(); i < size; i++) {
                String chord = chordList.get(i);
                chord = TabUtil.transferChord(chord, offset);
                chordList.set(i, chord);
            }
        }
        return this;
    }

}
