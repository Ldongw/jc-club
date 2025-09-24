package com.don.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.don.auth.application.convert.AuthPermissionDTOConverter;
import com.don.auth.application.convert.AuthRoleDTOConverter;
import com.don.auth.application.dto.AuthPermissionDTO;
import com.don.auth.application.dto.AuthRoleDTO;
import com.don.auth.common.entity.Result;
import com.don.auth.domain.entity.AuthPermissionBO;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.service.AuthPermissionDomainService;
import com.don.auth.domain.service.AuthRoleDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author A
 * @date 2025/9/22
 **/
@RestController
@Slf4j
@RequestMapping("/permission/")
public class PermissionController {


    @Autowired
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 新增角色
     * @param authPermissionDTO
     * @return
     */

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不能为空！");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "权限父亲 ID 不能为空！");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e){
            log.error("PermissionController.add.error:{}", e.getMessage());
            return Result.fail("新增角色失败！");
        }
    }

    /**
     * 修改权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限 id 不能为空！");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e){
            log.error("PermissionController.update.error:{}", e.getMessage());
            return Result.fail("更新权限失败！");
        }
    }

    /**
     * 删除用户
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e){
            log.error("PermissionController.delete.error:{}", e.getMessage());
            return Result.fail("删除权限失败！");
        }
    }

}
