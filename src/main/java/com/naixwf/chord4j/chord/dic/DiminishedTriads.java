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
 * 减三和弦
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public class DiminishedTriads extends Triads {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DiminishedTriads.class);

    /**
     * @param root
     */
    public DiminishedTriads(Note root) {
        super(root);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getIntervalList()
     */
    @Override
    protected List<Interval> getIntervalList() {
        return Arrays.asList(Interval.m3, Interval.d5);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getPostfix()
     */
    @Override
    public String getPostfix() {
        return ChordPostfix.DIMINISHED_TRIAD.getMain();
    }

}
