package com.don.auth.application.convert;

import com.don.auth.application.dto.AuthPermissionDTO;
import com.don.auth.domain.entity.AuthPermissionBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T19:27:38+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class AuthPermissionDTOConverterImpl implements AuthPermissionDTOConverter {

    @Override
    public AuthPermissionBO convertDTOToBO(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBO authPermissionBO = new AuthPermissionBO();

        authPermissionBO.setId( authPermissionDTO.getId() );
        authPermissionBO.setName( authPermissionDTO.getName() );
        authPermissionBO.setParentId( authPermissionDTO.getParentId() );
        authPermissionBO.setType( authPermissionDTO.getType() );
        authPermissionBO.setMenuUrl( authPermissionDTO.getMenuUrl() );
        authPermissionBO.setStatus( authPermissionDTO.getStatus() );
        authPermissionBO.setShow( authPermissionDTO.getShow() );
        authPermissionBO.setIcon( authPermissionDTO.getIcon() );
        authPermissionBO.setPermissionKey( authPermissionDTO.getPermissionKey() );
        authPermissionBO.setCreatedBy( authPermissionDTO.getCreatedBy() );
        authPermissionBO.setCreatedTime( authPermissionDTO.getCreatedTime() );
        authPermissionBO.setUpdateBy( authPermissionDTO.getUpdateBy() );
        authPermissionBO.setUpdateTime( authPermissionDTO.getUpdateTime() );
        authPermissionBO.setIsDeleted( authPermissionDTO.getIsDeleted() );

        return authPermissionBO;
    }
}
