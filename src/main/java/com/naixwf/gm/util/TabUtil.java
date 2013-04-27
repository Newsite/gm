/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.naixwf.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.chord.Note;
import com.naixwf.gm.web.vo.Sentence;
import com.naixwf.gm.web.vo.TabContentVo;

/**
 * @author wangfei
 * @created 2013-1-22
 * 
 * @version 1.0
 */
public class TabUtil {
    private static final Logger logger = LoggerFactory.getLogger(TabUtil.class);

    @SuppressWarnings("unchecked")
    public static TabContentVo jsonToTabContent(String json) {
        TabContentVo vo = new TabContentVo();
        List<?> tmp = JsonUtil.toList(json);
        for (Object o : tmp) {
            Map<String, Object> map = (Map<String, Object>) o;
            List<String> chordStrList = (List<String>) map.get("chordList");
            List<Chord> chordList = strList2ChordList(chordStrList);
            String lyric = (String) map.get("lyric");
            Sentence s = new Sentence(chordList, lyric);
            vo.add(s);
        }
        return vo;
    }

    /**
     * 将字符串列表转换为和弦列表
     * 
     * @author wangfei
     * @param str
     * @return
     */
    private static List<Chord> strList2ChordList(List<String> str) {
        List<Chord> list = new ArrayList<Chord>();
        if (str != null) {
            for (String s : str) {
                list.add(Chord.string2Chord(s));
            }
        }
        return list;
    }

   

    /**
     * @author wangfei
     * @param chord
     * @param offset
     * @return
     */
    public static Chord transferChord(final Chord chord, int offset) {
        Note root = chord.getRootNote();
        Note newRoot = root.add(offset);
        Chord newChord = Chord.newChord(newRoot,chord.getPostfix());
        return newChord;
    }
}
