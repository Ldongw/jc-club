package com.don.auth.domain.service.impl;

import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.converter.AuthPermissionBOConverter;
import com.don.auth.domain.converter.AuthRoleBOConverter;
import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.redis.RedisUtil;
import com.don.auth.domain.service.AuthPermissionDomainService;
import com.don.auth.domain.service.AuthRoleDomainService;
import com.don.auth.infra.basic.entity.AuthPermission;
import com.don.auth.infra.basic.entity.AuthRole;
import com.don.auth.infra.basic.service.AuthPermissionService;
import com.don.auth.infra.basic.service.AuthRoleService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author A
 * @date 2025/9/21
 **/
@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Autowired
    private AuthPermissionService authPermissionService;


    @Autowired
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";


    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if(StringUtils.isBlank(permissionValue))
            return Collections.emptyList();
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue, new TypeToken<List<AuthPermission>>() {
        }.getType());

        return permissionList.stream().map(AuthPermission::getPermissionKey).toList();
    }
}
