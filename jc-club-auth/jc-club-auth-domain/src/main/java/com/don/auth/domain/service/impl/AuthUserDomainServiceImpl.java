package com.don.auth.domain.service.impl;

import com.don.auth.common.enums.AuthUserStatusEnum;
import com.don.auth.common.enums.IsDeletedFlagEnum;
import com.don.auth.domain.converter.AuthUserBOConverter;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.service.AuthUserDomainService;
import com.don.auth.infra.basic.entity.AuthUser;
import com.don.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author A
 * @date 2025/9/21
 **/
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Autowired
    private AuthUserService authUserService;
    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        Integer count = authUserService.insert(authUser);
        return count > 0;
    }
}
