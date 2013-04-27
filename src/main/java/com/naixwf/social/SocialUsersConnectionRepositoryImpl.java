package com.naixwf.social;

import com.naixwf.social.dao.AccountDao;
import com.naixwf.social.dao.SocialConnectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-18
 * Time: 下午5:23
 * http://harmonicdevelopment.tumblr.com/post/13613051804/adding-spring-social-to-a-spring-mvc-and-spring
 */

public class SocialUsersConnectionRepositoryImpl implements UsersConnectionRepository {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialUsersConnectionRepositoryImpl.class);

    private SocialConnectionDao socialConnectionDao;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;

    private ConnectionSignUp connectionSignUp;

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }

    public SocialUsersConnectionRepositoryImpl(SocialConnectionDao socialUserDao, ConnectionFactoryLocator connectionFactoryLocator, TextEncryptor textEncryptor) {
        this.socialConnectionDao = socialUserDao;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
    }


    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        List<String> localUserIds = socialConnectionDao.findUserIdsByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId());

        if (localUserIds.size() == 0 && connectionSignUp != null) {//如果没有account，那么直接注册一个,返回注册之后的结果
            String newUserId = connectionSignUp.execute(connection);
            if (newUserId != null) {
                createConnectionRepository(newUserId).addConnection(connection);
                return Arrays.asList(newUserId);
            }
        }
        return localUserIds;
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        List<String> localUserIds = socialConnectionDao.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds);
        return new HashSet<String>(localUserIds);
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        ConnectionRepositoryImpl connectionRepository = new ConnectionRepositoryImpl(userId, socialConnectionDao, connectionFactoryLocator, textEncryptor);
        connectionRepository.setAccountDao(accountDao);
        return connectionRepository;

    }
}
