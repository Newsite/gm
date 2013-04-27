package com.naixwf.social;

import com.naixwf.social.dao.AccountDao;
import com.naixwf.social.dao.SocialConnectionDao;
import com.naixwf.social.domain.Account;
import com.naixwf.social.domain.SocialConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午3:20
 * 注册一个新用户，并显示问题
 */

public class ConnectionSignUpImpl implements ConnectionSignUp {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ConnectionSignUpImpl.class);

    private AccountDao accountDao;

    public ConnectionSignUpImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public String execute(Connection<?> connection) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString().replace("-", ""));
        account.setName(connection.getDisplayName());
        accountDao.insert(account);
        return account.getId();
    }
}
