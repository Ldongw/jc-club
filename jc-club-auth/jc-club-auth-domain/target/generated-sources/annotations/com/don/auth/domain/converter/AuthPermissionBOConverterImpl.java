package com.don.auth.domain.converter;

import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.infra.basic.entity.AuthPermission;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T20:37:04+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class AuthPermissionBOConverterImpl implements AuthPermissionBOConverter {

    @Override
    public AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO) {
        if ( authPermissionBO == null ) {
            return null;
        }

        AuthPermission authPermission = new AuthPermission();

        authPermission.setId( authPermissionBO.getId() );
        authPermission.setName( authPermissionBO.getName() );
        authPermission.setParentId( authPermissionBO.getParentId() );
        authPermission.setType( authPermissionBO.getType() );
        authPermission.setMenuUrl( authPermissionBO.getMenuUrl() );
        authPermission.setStatus( authPermissionBO.getStatus() );
        authPermission.setShow( authPermissionBO.getShow() );
        authPermission.setIcon( authPermissionBO.getIcon() );
        authPermission.setPermissionKey( authPermissionBO.getPermissionKey() );
        authPermission.setCreatedBy( authPermissionBO.getCreatedBy() );
        authPermission.setCreatedTime( authPermissionBO.getCreatedTime() );
        authPermission.setUpdateBy( authPermissionBO.getUpdateBy() );
        authPermission.setUpdateTime( authPermissionBO.getUpdateTime() );
        authPermission.setIsDeleted( authPermissionBO.getIsDeleted() );

        return authPermission;
    }
}
