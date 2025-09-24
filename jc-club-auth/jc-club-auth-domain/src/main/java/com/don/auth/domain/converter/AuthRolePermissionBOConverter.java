package com.don.auth.domain.converter;

import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRolePermissionBO;
import com.don.auth.infra.basic.entity.AuthPermission;
import com.don.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/9/21
 **/
@Mapper
public interface AuthRolePermissionBOConverter {

    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);

}
