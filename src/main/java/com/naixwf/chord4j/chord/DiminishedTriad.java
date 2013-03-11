/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

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
public class DiminishedTriad extends Triad {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DiminishedTriad.class);

    /**
     * @param root
     */
    public DiminishedTriad(Note root) {
        super(root);
    }

}
