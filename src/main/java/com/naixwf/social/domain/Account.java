package com.naixwf.social.domain;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午3:16
 */
@Entity
@Table(name = "account")
public class Account {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Account.class);
    private String id;
    private String name;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
