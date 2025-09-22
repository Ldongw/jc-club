package com.don.auth.application.convert;

import com.don.auth.application.dto.AuthRoleDTO;
import com.don.auth.application.dto.AuthUserDTO;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/9/21
 **/
@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDTOToBO(AuthRoleDTO authRoleDTO);
}
