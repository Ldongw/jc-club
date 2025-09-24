package com.don.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.don.auth.application.convert.AuthPermissionDTOConverter;
import com.don.auth.application.convert.AuthRolePermissionDTOConverter;
import com.don.auth.application.dto.AuthPermissionDTO;
import com.don.auth.application.dto.AuthRolePermissionDTO;
import com.don.auth.common.entity.Result;
import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRolePermissionBO;
import com.don.auth.domain.service.AuthPermissionDomainService;
import com.don.auth.domain.service.AuthRolePermissionDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 角色权限 Controller
 * @author A
 * @date 2025/9/22
 **/
@RestController
@Slf4j
@RequestMapping("/rolePermission/")
public class RolePermissionController {


    @Autowired
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色
     * @param authRolePermissionDTO
     * @return
     */

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限关联不能为空！");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色不能为空！");


            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        }catch (Exception e){
            log.error("RolePermissionController.add.error:{}", e.getMessage());
            return Result.fail("新增角色权限失败！");
        }
    }

}
