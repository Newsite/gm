/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 增三和弦
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public class AugmentedTriad extends Triad {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AugmentedTriad.class);

    /**
     * @param root
     */
    public AugmentedTriad(Note root) {
        super(root);
    }

}
