package com.naixwf.social;

import com.naixwf.social.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午4:20
 */

public class SignInAdapterImpl implements SignInAdapter {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SignInAdapterImpl.class);

    private UsersConnectionRepository usersConnectionRepository;
    private SecurityService securityService;

    public SignInAdapterImpl(UsersConnectionRepository usersConnectionRepository, SecurityService securityService) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.securityService = securityService;
    }

    /**
     * 在connection库中找到这个连接对应的userId,且和传入的userId匹配,则认为登陆成功
     *
     * @param userId
     * @param connection
     * @param request
     * @return
     */
    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
        if (userIds != null && userIds.contains(userId)) {
            securityService.setAuthenticationByUserId(userId);
        }
        return null;
    }
}
