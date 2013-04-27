package com.naixwf.core.auditlog;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-3-15
 * Time: 下午2:48
 * domain类可用，加了这个标记的字段不计auditLog
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface IgnoreAuditLog {
}
