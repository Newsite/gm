/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 9和弦
 * 
 * @author wangfei
 * @created 2013-2-19
 * 
 * @version 1.0
 */
public class NormalNinth extends Ninth {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(NormalNinth.class);

    /**
     * @param root
     */
    public NormalNinth(Note root) {
        super(root);
    }

}
