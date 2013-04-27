package com.naixwf.gm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午5:32
 */
@Controller
public class AccountController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
//    @Resource
//    private ConnectionFactoryRegistry connectionFactoryRegistry;

    /**
     * 进入登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {//already signedIn
            return "redirect:/";
        }

//        Collection<String> providerIdSet = connectionFactoryRegistry.registeredProviderIds();
        model.addAttribute("providerIdSet", Arrays.asList("weibo"));

        return "account/signin";
    }
}
