package com.don.auth.domain.service;

import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRolePermissionBO;

/**
 *
 * 角色领域 Service
 *
 * @author A
 * @date 2025/9/21
 **/
public interface AuthRolePermissionDomainService {

    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}
