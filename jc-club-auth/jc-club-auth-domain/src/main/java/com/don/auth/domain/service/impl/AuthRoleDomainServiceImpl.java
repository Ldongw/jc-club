package com.don.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.don.auth.common.enums.AuthUserStatusEnum;
import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.converter.AuthRoleBOConverter;
import com.don.auth.domain.converter.AuthUserBOConverter;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.service.AuthRoleDomainService;
import com.don.auth.domain.service.AuthUserDomainService;
import com.don.auth.infra.basic.entity.AuthRole;
import com.don.auth.infra.basic.entity.AuthUser;
import com.don.auth.infra.basic.service.AuthRoleService;
import com.don.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author A
 * @date 2025/9/21
 **/
@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Autowired
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        Integer count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authUserBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authUserBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authUserBO.getId());
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }
}
