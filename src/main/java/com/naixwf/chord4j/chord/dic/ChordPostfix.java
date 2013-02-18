/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

import java.util.Arrays;
import java.util.List;

/**
 * 和弦后缀名
 * http://zh.wikipedia.org/wiki/%E4%B8%89%E5%92%8C%E5%BC%A6#.E4.B8.89.E5.92
 * .8C.E5.BC.A6
 * 
 * @author wangfei
 * @created 2013-2-17
 * 
 * @version 1.0
 */
public enum ChordPostfix {
    MAJOR_TRIAD(Arrays.asList("", "M", "maj", "△")), MINOR_TRIAD(Arrays.asList("m", "min", "-",
            "-△")), AUGMENTED_TRIAD(Arrays.asList("aug", "+", "+5", "(#5)", "（#5）", "+△", "△+5",
            "△(#5)", "△（#5）")), DIMINISHED_TRIAD(Arrays.asList("dim", "o", "o△", "m-5", "m（♭5）",
            "m(♭5)")), DOMINANT_SEVENTH(Arrays.asList("7", "Mm7"));
    private List<String> list;

    private ChordPostfix(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public String getMain() {
        return list.get(0);
    }

}
