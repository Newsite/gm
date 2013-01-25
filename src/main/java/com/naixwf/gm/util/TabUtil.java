/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.dic.Pitch;
import com.naixwf.chord4j.chord.dic.ToneMarker;
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
            // logger.debug(o.toString());
            List<String> chordList = (List<String>) map.get("chordList");
            String lyric = (String) map.get("lyric");
            Sentence s = new Sentence(chordList, lyric);
            vo.add(s);
        }
        return vo;
    }

    /**
     * @author wangfei
     * @param chord
     * @param offset
     * @return
     */
    public static String transferChord(String chord, int offset) {
        Pitch x = null;// 最终生成的音高，name被用于替换大些字母
        String pitchName = null;// 大写字母
        for (int j = 0, l = chord.length(); j < l; j++) {
            char tmp = chord.charAt(j);
            if (tmp >= 'A' && tmp <= 'Z') {// 找到大写字母
                pitchName = tmp + "";
                if (chord.contains(ToneMarker.SHARP.getName() + "")) {
                    pitchName = ToneMarker.SHARP.getName() + "" + tmp;
                }
                if (chord.contains(ToneMarker.FLAT.getName() + "")) {
                    pitchName = ToneMarker.FLAT.getName() + "" + tmp;
                }
                x = new Pitch(pitchName);
                // logger.debug("before:" + x.getName());
                x = x.add(offset);
                // logger.debug("after:" + x.getName());
                break;
            }
        }
        if (x == null) {
            logger.info(chord + "+" + offset);
        }

        chord = chord.replaceAll(ToneMarker.FLAT.getName() + "", "");
        // logger.debug("去掉flat:" + chord);

        chord = chord.replaceAll(ToneMarker.SHARP.getName() + "", "");
        // logger.debug("去掉sharp:" + chord);

        chord = chord.replaceAll(pitchName, x.getName());
        // logger.debug(pitchName + ":" + x.getName());

        // logger.debug("after:" + chord);
        // logger.debug("");
        return chord;
    }
}
