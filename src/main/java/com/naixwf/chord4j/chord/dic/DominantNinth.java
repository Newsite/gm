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
 * 属9和弦
 * 
 * @author wangfei
 * @created 2013-2-18
 * 
 * @version 1.0
 */
public class DominantNinth extends Ninth {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DominantNinth.class);

    /**
     * @param root
     */
    protected DominantNinth(Note root) {
        super(root);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getIntervalList()
     */
    @Override
    protected List<Interval> getIntervalList() {
        // return Arrays.asList(Interval.M3, Interval.P5,
        // Interval.m7,Interval.);
        return null;
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getPostfix()
     */
    @Override
    public String getPostfix() {
        return ChordPostfix.DOMINANT_NINTH.getMain();
    }
}
