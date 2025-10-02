package com.don.auth.application.convert;

import com.don.auth.application.dto.AuthUserDTO;
import com.don.auth.domain.entity.AuthUserBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T20:37:07+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class AuthUserDTOConverterImpl implements AuthUserDTOConverter {

    @Override
    public AuthUserBO convertDTOToBO(AuthUserDTO authUserDTO) {
        if ( authUserDTO == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUserDTO.getId() );
        authUserBO.setUserName( authUserDTO.getUserName() );
        authUserBO.setNickName( authUserDTO.getNickName() );
        authUserBO.setEmail( authUserDTO.getEmail() );
        authUserBO.setPhone( authUserDTO.getPhone() );
        authUserBO.setPassword( authUserDTO.getPassword() );
        authUserBO.setSex( authUserDTO.getSex() );
        authUserBO.setAvatar( authUserDTO.getAvatar() );
        authUserBO.setStatus( authUserDTO.getStatus() );
        authUserBO.setIntroduce( authUserDTO.getIntroduce() );
        authUserBO.setExtJson( authUserDTO.getExtJson() );
        authUserBO.setCreatedBy( authUserDTO.getCreatedBy() );
        authUserBO.setCreatedTime( authUserDTO.getCreatedTime() );
        authUserBO.setUpdateBy( authUserDTO.getUpdateBy() );
        authUserBO.setUpdateTime( authUserDTO.getUpdateTime() );
        authUserBO.setIsDeleted( authUserDTO.getIsDeleted() );

        return authUserBO;
    }

    @Override
    public AuthUserDTO convertBOToDTO(AuthUserBO userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        AuthUserDTO authUserDTO = new AuthUserDTO();

        authUserDTO.setId( userInfo.getId() );
        authUserDTO.setUserName( userInfo.getUserName() );
        authUserDTO.setNickName( userInfo.getNickName() );
        authUserDTO.setEmail( userInfo.getEmail() );
        authUserDTO.setPhone( userInfo.getPhone() );
        authUserDTO.setPassword( userInfo.getPassword() );
        authUserDTO.setSex( userInfo.getSex() );
        authUserDTO.setAvatar( userInfo.getAvatar() );
        authUserDTO.setStatus( userInfo.getStatus() );
        authUserDTO.setIntroduce( userInfo.getIntroduce() );
        authUserDTO.setExtJson( userInfo.getExtJson() );
        authUserDTO.setCreatedBy( userInfo.getCreatedBy() );
        authUserDTO.setCreatedTime( userInfo.getCreatedTime() );
        authUserDTO.setUpdateBy( userInfo.getUpdateBy() );
        authUserDTO.setUpdateTime( userInfo.getUpdateTime() );
        authUserDTO.setIsDeleted( userInfo.getIsDeleted() );

        return authUserDTO;
    }
}
