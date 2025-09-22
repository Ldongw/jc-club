package com.don.auth.domain.service;

import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.entity.AuthUserBO;

/**
 *
 * 角色领域 Service
 *
 * @author A
 * @date 2025/9/21
 **/
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authUserBO);

    Boolean delete(AuthRoleBO authUserBO);
}
