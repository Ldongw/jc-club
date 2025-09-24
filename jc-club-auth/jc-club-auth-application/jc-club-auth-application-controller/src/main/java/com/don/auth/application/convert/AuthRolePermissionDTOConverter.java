package com.don.auth.application.convert;

import com.don.auth.application.dto.AuthPermissionDTO;
import com.don.auth.application.dto.AuthRolePermissionDTO;
import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/9/21
 **/
@Mapper
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
