/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 七和弦
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public abstract class Seventh extends Chord {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Seventh.class);

    /**
     * @param root
     */
    protected Seventh(Note root) {
        super(root);
    }

    /**
     * @author wangfei
     * @return
     * @see com.naixwf.chord4j.chord.dic.Chord#getNoteNum()
     */
    @Override
    protected Integer getNoteNum() {
        return 4;
    }
}
