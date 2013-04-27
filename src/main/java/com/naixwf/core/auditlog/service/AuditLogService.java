/*
 * @author wangfei
 */
package com.naixwf.core.auditlog.service;

import java.lang.reflect.InvocationTargetException;

/**
 * 与审计日志相关的服务
 * 
 * @author wangfei
 * @created 2013-3-14
 * 
 * @version 1.0
 */
public interface AuditLogService {

    /**
     * 插入新的log
     * 
     * @author wangfei
     * @param newObj
     * @param isInsert
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws java.lang.reflect.InvocationTargetException
     */
    void insertLogByEntity(Object newObj, Boolean isInsert) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

}
