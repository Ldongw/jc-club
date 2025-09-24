package com.don.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.don.auth.common.enums.AuthUserStatusEnum;
import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.constants.AuthConstant;
import com.don.auth.domain.converter.AuthUserBOConverter;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.service.AuthUserDomainService;
import com.don.auth.infra.basic.entity.AuthRole;
import com.don.auth.infra.basic.entity.AuthUser;
import com.don.auth.infra.basic.entity.AuthUserRole;
import com.don.auth.infra.basic.service.AuthRoleService;
import com.don.auth.infra.basic.service.AuthUserRoleService;
import com.don.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private String salt = "don";
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        Integer count = authUserService.insert(authUser);
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(roleResult.getId());
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        authUserRoleService.insert(authUserRole);

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
}
