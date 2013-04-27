/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.dao;

import com.naixwf.core.dao.HibernateBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.naixwf.gm.domain.Jitapu;

/**
 * 
 * @author wangfei
 * @created 2012-9-4
 * 
 * @version 1.0
 */
@Repository
public class JitapuDao extends HibernateBaseDao<Jitapu> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(JitapuDao.class);
}
