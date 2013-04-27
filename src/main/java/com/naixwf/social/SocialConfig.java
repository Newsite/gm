package com.naixwf.social;

import com.naixwf.social.dao.AccountDao;
import com.naixwf.social.dao.SocialConnectionDao;
import com.naixwf.social.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.*;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.weibo.connect.WeiboConnectionFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 上午11:46
 * social 相关的spring bean初始化配置
 */
@Configuration
public class SocialConfig {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialConfig.class);

    @Bean
    public SocialConnectionDao socialConnectionDao() {
        return new SocialConnectionDao();
    }

    @Bean
    public AccountDao accountDao() {
        return new AccountDao();
    }

    @Bean
    public ConnectionSignUp connectionSignUp() {
        return new ConnectionSignUpImpl(accountDao());
    }

    @Inject
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * @return
     */
    @Bean
    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository() {
        SocialUsersConnectionRepositoryImpl repository = new SocialUsersConnectionRepositoryImpl(socialConnectionDao(), connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(connectionSignUp());
        repository.setAccountDao(accountDao());
        return repository;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("用户尚未登陆");
        }
        return usersConnectionRepository().createConnectionRepository(authentication.getName());
    }


    @Bean
    public WeiboConnectionFactory weiboConnectionFactory() {
        return new WeiboConnectionFactory("3513144720", "a4fb8f3984789d96e35d877c2bdf205c");
    }

    @Bean
    public ConnectionFactoryRegistry connectionFactoryRegistry() {
        ConnectionFactoryRegistry result = new ConnectionFactoryRegistry();

        List<ConnectionFactory<?>> connectionFactoryList = new ArrayList<ConnectionFactory<?>>();
        connectionFactoryList.add(weiboConnectionFactory());

        result.setConnectionFactories(connectionFactoryList);
        return result;
    }

    /**
     * 提供注册、登陆服务
     *
     * @return
     */
    @Bean
    public ProviderSignInController providerSignInController() {
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository(), signInAdapter());
        providerSignInController.setApplicationUrl("http://guitarme.wangfeing.cn");
        return providerSignInController;
    }

    @Bean
    public SecurityService securityService() {
        return new SecurityService();
    }

    @Bean
    public SignInAdapterImpl signInAdapter() {
        return new SignInAdapterImpl(usersConnectionRepository(), securityService());
    }


}
