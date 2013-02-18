/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小七和弦
 * 
 * @author wangfei
 * @created 2013-2-18
 * 
 * @version 1.0
 */
public class MinorSeventh extends Seventh {
    /**
     * @param root
     */
    public MinorSeventh(Note root) {
        super(root);
    }

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MinorSeventh.class);

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getIntervalList()
     */
    @Override
    protected List<Interval> getIntervalList() {
        return Arrays.asList(Interval.m3, Interval.P5, Interval.m7);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getPostfix()
     */
    @Override
    public String getPostfix() {
        return ChordPostfix.MINOR_SEVENTH.getMain();
    }
}
