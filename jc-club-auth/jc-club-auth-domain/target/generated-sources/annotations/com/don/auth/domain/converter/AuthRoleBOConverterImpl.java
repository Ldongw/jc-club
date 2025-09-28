package com.don.auth.domain.converter;

import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.infra.basic.entity.AuthRole;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T11:22:04+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class AuthRoleBOConverterImpl implements AuthRoleBOConverter {

    @Override
    public AuthRole convertBOToEntity(AuthRoleBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authUserBO.getId() );
        authRole.setRoleName( authUserBO.getRoleName() );
        authRole.setRoleKey( authUserBO.getRoleKey() );

        return authRole;
    }
}
