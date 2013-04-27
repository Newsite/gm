/*
 * @author wangfei
 */
package com.naixwf.core.auditlog.dao;

import com.naixwf.core.dao.HibernateBaseDao;
import com.naixwf.core.auditlog.domain.AuditLog;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangfei
 * @version 1.0
 * @created 2013-3-14
 */
@Repository
public class AuditLogDao extends HibernateBaseDao<AuditLog> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuditLogDao.class);

    /**
     * 根据entity查找审计日志，以时间排序
     *
     * @param entityName
     * @param entityId
     * @return
     * @author wangfei
     */
    public List<AuditLog> listByEntity(String entityName, String entityId) {
        DetachedCriteria crit = DetachedCriteria.forClass(AuditLog.class);
        crit.add(Restrictions.eq("entityName", entityName))
                .add(Restrictions.eq("entityId", entityId))
                .addOrder(Order.asc("createTime"));
        return list(crit);
    }
}
