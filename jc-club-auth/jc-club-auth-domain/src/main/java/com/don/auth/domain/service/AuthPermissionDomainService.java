package com.don.auth.domain.service;

import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRoleBO;

import java.util.List;

/**
 *
 * 角色领域 Service
 *
 * @author A
 * @date 2025/9/21
 **/
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
