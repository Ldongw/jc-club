package com.don.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.don.auth.domain.entity.AuthUserBO;

/**
 * @author A
 * @date 2025/9/21
 **/
public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBO
     * @return
     */
    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
