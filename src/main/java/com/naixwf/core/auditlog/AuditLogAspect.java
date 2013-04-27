/*
 * @author wangfei
 */
package com.naixwf.core.auditlog;

import com.naixwf.core.util.JavaClassUtil;
import com.naixwf.core.auditlog.service.AuditLogService;
import com.naixwf.core.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 业务审计日志切面
 *
 * @author wangfei
 * @version 1.0
 * @created 2013-3-14
 */
public class AuditLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);
    // 读写log的服务
    private AuditLogService auditLogService;
    // domain包名
    private String domainPackageName;
    // 不计log的domain,只写短类名
    private String[] ignoreDomainList;
    // 所有需要记log的domain类
    private List<Class<?>> domainNeedLog;

    // 需要被记录的dao方法名
    private List<String> auditLogableMethods;

    public void setDomainPackageName(String domainPackageName) {
        this.domainPackageName = domainPackageName;
    }

    public void setIgnoreDomainList(String[] ignoreDomainList) {
        this.ignoreDomainList = ignoreDomainList;
    }


    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    public AuditLogAspect() {
    }

    @PostConstruct
    public void init() {
        // 默认只处理名称为insert、update的dao方法
        auditLogableMethods = Arrays.asList("persist", "merge");
        domainNeedLog = initDomainNeedLog(domainPackageName, ignoreDomainList);
    }

    /**
     * 根据domain包名和忽略类名生成需要记log的domain 列表
     *
     * @param domainPackageName
     * @param ignoreDomainList
     * @return
     * @author wangfei
     */
    private List<Class<?>> initDomainNeedLog(String domainPackageName, String[] ignoreDomainList) {
        if (StringUtil.isBlank(domainPackageName)) {
            throw new RuntimeException("property domainPackageName can not be empty");
        }

        List<Class<?>> clazzList = JavaClassUtil.getClassNameByPackageName(domainPackageName);

        List<Class<?>> list = new ArrayList<Class<?>>();
        logger.info("将要被记录审计日志的domain:");
        if (clazzList != null) {
            for (Class<?> clazz : clazzList) {
                boolean flag = false;
                if (ignoreDomainList != null) {
                    for (String ignore : ignoreDomainList) {
                        clazz.getSimpleName().equals(ignore);
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    logger.info(clazz.getName());
                    list.add(clazz);
                }

            }
        }

        return list;
    }

    /**
     * 处理所有的auditlog通知
     *
     * @param jp
     * @throws Throwable
     * @author wangfei
     */
    public void afterDaoMethod(JoinPoint jp) {
        String methodName = ((MethodSignature) jp.getSignature()).getName();

        if (!isAuditLogableMethod(methodName)) {
            return;
        }

        Object newObj = getNewObj(jp.getArgs());
        if (newObj == null) {
            return;
        }

        Boolean isInsert = false;
        if (methodName.equals("persist")) {
            isInsert = true;
        }
        try {
            auditLogService.insertLogByEntity(newObj, isInsert);
        } catch (Exception e) {
            throw new RuntimeException("insert auditLog error:", e);
        }
    }

    /**
     * 获取被更改后的实体
     *
     * @param args
     * @return
     * @author wangfei
     */
    private Object getNewObj(Object[] args) {
        // 只有参数为1，且此参数类型在domainNeedLog列表中的，才记log
        if (args.length == 1) {
            Object obj = args[0];
            if (domainNeedLog.contains(obj.getClass())) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 是需要记log的方法
     *
     * @param method
     * @return
     * @author wangfei
     */
    private boolean isAuditLogableMethod(String method) {
        return auditLogableMethods.contains(method);
    }
}
