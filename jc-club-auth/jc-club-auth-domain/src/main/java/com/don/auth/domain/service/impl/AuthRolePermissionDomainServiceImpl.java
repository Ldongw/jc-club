package com.don.auth.domain.service.impl;

import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.converter.AuthPermissionBOConverter;
import com.don.auth.domain.converter.AuthRolePermissionBOConverter;
import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRolePermissionBO;
import com.don.auth.domain.service.AuthPermissionDomainService;
import com.don.auth.domain.service.AuthRolePermissionDomainService;
import com.don.auth.infra.basic.entity.AuthPermission;
import com.don.auth.infra.basic.entity.AuthRolePermission;
import com.don.auth.infra.basic.service.AuthPermissionService;
import com.don.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author A
 * @date 2025/9/21
 **/
@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Autowired
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        List<Long> permissionIdList = authRolePermissionBO.getPermissionIdList();
        for(Long permissionId:permissionIdList){
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            rolePermissionList.add(authRolePermission);
        }
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }

}
