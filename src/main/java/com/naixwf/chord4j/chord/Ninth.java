/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 九和弦 http://en.wikipedia.org/wiki/Ninth_chord
 * 
 * @author wangfei
 * @created 2013-2-18
 * 
 * @version 1.0
 */
public abstract class Ninth extends Chord {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Ninth.class);

    /**
     * @param root
     */
    protected Ninth(Note root) {
        super(root);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.Chord#getNoteNum()
     */
    @Override
    protected Integer getNoteNum() {
        return 5;
    }
}
