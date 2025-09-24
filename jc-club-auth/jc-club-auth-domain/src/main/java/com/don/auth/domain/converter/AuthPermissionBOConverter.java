package com.don.auth.domain.converter;

import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.infra.basic.entity.AuthPermission;
import com.don.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/9/21
 **/
@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO);

}
