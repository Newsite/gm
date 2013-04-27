/*
 * @author wangfei
 */
package com.naixwf.core.auditlog.service.impl;

import com.naixwf.core.auditlog.IgnoreAuditLog;
import com.naixwf.core.auditlog.dao.AuditLogDao;
import com.naixwf.core.auditlog.domain.AuditLog;
import com.naixwf.core.auditlog.service.AuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 默认的审计日志实现，日志读写到数据库
 *
 * @author wangfei
 * @version 1.0
 * @created 2013-3-14
 */
public class DefaultAuditLogService implements AuditLogService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuditLogService.class);
    @Resource
    private AuditLogDao auditLogDao;

    /**
     * @param newObj
     * @param isInsert
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws java.lang.reflect.InvocationTargetException
     *
     * @author wangfei
     * @see com.naixwf.core.auditlog.service.AuditLogService#insertLogByEntity(Object,
     *      Boolean)
     */
    @Override
    public void insertLogByEntity(Object newObj, Boolean isInsert) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Class<?> clazz = newObj.getClass();
        String entityName = clazz.getSimpleName();
        String entityId = getIdValue(newObj).toString();
        String accessLogId = "random logId";    //TODO
        Integer modifierId = 0;//TODO

        Date date = new Date();

        List<AuditLog> newLogList = new ArrayList<AuditLog>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(IgnoreAuditLog.class) != null && field.getAnnotation(Transient.class) != null) {
                continue;
            }
            field.setAccessible(true);
            AuditLog log = new AuditLog();
            log.setAccessLogId(getStringValue(accessLogId));
            log.setModifierId(modifierId == null ? 0 : modifierId);
            log.setCreateTime(date);
            log.setEntityId(entityId);
            log.setEntityName(entityName);
            log.setFieldName(field.getName());
            log.setNewValue(getStringValue(field.get(newObj)));

            newLogList.add(log);
        }

        Map<String, AuditLog> oldMap = new HashMap<String, AuditLog>();
        if (!isInsert) {
            oldMap = mapLastByEntity(entityName, entityId);
        }

        List<AuditLog> resultList = new ArrayList<AuditLog>();
        for (AuditLog log : newLogList) {
            AuditLog oldLog = oldMap.get(log.getFieldName());
            if (oldLog == null) {
                log.setOldValue(getStringValue(null));
                resultList.add(log);
            } else {
                String oldValue = oldLog.getNewValue();
                if (!log.getNewValue().equals(oldValue)) {
                    log.setOldValue(oldValue);
                    resultList.add(log);
                }
            }
        }

        auditLogDao.insert(resultList);
    }

    /**
     * 获取object的字符串值
     *
     * @param o
     * @return
     */
    private String getStringValue(Object o) {
        return o == null ? "" : String.valueOf(o);
    }

    /**
     * 获取所有字段的最后值的map
     *
     * @param entityName
     * @param entityId
     * @return
     * @author wangfei
     */
    private Map<String, AuditLog> mapLastByEntity(String entityName, String entityId) {
        Map<String, AuditLog> map = new HashMap<String, AuditLog>();

        List<AuditLog> list = auditLogDao.listByEntity(entityName, entityId);
        if (list != null) {
            for (AuditLog log : list) {
                map.put(log.getFieldName(), log);
            }
        }
        return map;
    }

    private Serializable getIdValue(Object modifiedEntity) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Class<?> clazz = modifiedEntity.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            Id id = method.getAnnotation(Id.class);
            if (id != null) {
                method.setAccessible(true);
                return (Serializable) method.invoke(modifiedEntity);
            }
        }

        for (Field field : clazz.getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                field.setAccessible(true);
                return (Serializable) field.get(modifiedEntity);
            }
        }

        return null;
    }

}
