package com.naixwf.core.auditlog.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit_log")
@GenericGenerator(name = "uuid", strategy = "guid")
public class AuditLog {
    private String id;
    // 访问日志id，可能为可能为空
    private String accessLogId;
    // domain类型
    private String entityName;
    // domain 主键
    private String entityId;
    // 字段名称
    private String fieldName;
    // 字段旧值
    private String oldValue;
    // 字段新值
    private String newValue;
    // 修改者
    private Integer modifierId;
    // 修改时间
    private Date createTime;

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "access_log_id")
    public String getAccessLogId() {
        return this.accessLogId;
    }

    public void setAccessLogId(String accessLogId) {
        this.accessLogId = accessLogId;
    }

    @Column(name = "entity_name")
    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Column(name = "entity_id")
    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Column(name = "field_name")
    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Column(name = "old_value")
    public String getOldValue() {
        return this.oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Column(name = "new_value")
    public String getNewValue() {
        return this.newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Column(name = "modifier_id")
    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
