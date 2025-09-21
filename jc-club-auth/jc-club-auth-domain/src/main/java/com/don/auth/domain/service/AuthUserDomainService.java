package com.don.auth.domain.service;

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
}
