package com.don.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.don.auth.common.enums.AuthUserStatusEnum;
import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.constants.AuthConstant;
import com.don.auth.domain.converter.AuthUserBOConverter;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.redis.RedisUtil;
import com.don.auth.domain.service.AuthUserDomainService;
import com.don.auth.infra.basic.entity.*;
import com.don.auth.infra.basic.service.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A
 * @date 2025/9/21
 **/
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthUserRoleService authUserRoleService;

    @Autowired
    private AuthRolePermissionService authRolePermissionService;

    @Autowired
    private AuthPermissionService authPermissionService;

    private String salt = "don";

    private static final String LOGIN_PREFIX = "loginCode";

    @Autowired
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer existCount = authUserService.queryByConditionCount(authUser);
        if(existCount > 0)
            return true;
        if(StringUtils.isNotBlank(authUser.getPassword())) {
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        Integer count = authUserService.insert(authUser);

        // 角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(roleResult.getId());
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        authUserRoleService.insert(authUserRole);

        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new ArrayList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleResult.getId());
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);

        List<Long> permissionIdList = rolePermissionList
                .stream()
                .map(AuthRolePermission::getPermissionId)
                .toList();
        // roleId 查权限
//        List<AuthRole> authPermissionList = authRoleService.queryByRoleList(roleIdList);

        List<AuthPermission> authPermissionList = authPermissionService.queryByPermissionList(permissionIdList);

        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(authPermissionList));

        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
//        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
//        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(loginKey);
        if(StringUtils.isBlank(openId))
            return null;

        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        AuthUserDomainServiceImpl proxy = (AuthUserDomainServiceImpl) AopContext.currentProxy();
        proxy.register(authUserBO);
        StpUtil.login(openId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return tokenInfo;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        return AuthUserBOConverter.INSTANCE.convertEntityToBO(authUserService.queryByCondition(authUser));
    }
}
