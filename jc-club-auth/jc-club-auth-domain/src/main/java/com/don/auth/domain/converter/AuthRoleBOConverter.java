package com.don.auth.domain.converter;

import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.infra.basic.entity.AuthRole;
import com.don.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/9/21
 **/
@Mapper
public interface AuthRoleBOConverter {

    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convertBOToEntity(AuthRoleBO authUserBO);

}
