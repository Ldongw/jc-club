package com.don.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.don.auth.application.convert.AuthRoleDTOConverter;
import com.don.auth.application.convert.AuthUserDTOConverter;
import com.don.auth.application.dto.AuthRoleDTO;
import com.don.auth.application.dto.AuthUserDTO;
import com.don.auth.common.entity.Result;
import com.don.auth.domain.entity.AuthRoleBO;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.service.AuthRoleDomainService;
import com.don.auth.domain.service.AuthUserDomainService;
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
@RequestMapping("/role/")
public class RoleController {


    @Autowired
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 新增角色
     * @param authRoleDTO
     * @return
     */

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("UserController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色 Key 不能为空！");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空！");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e){
            log.error("UserController.add.error:{}", e.getMessage());
            return Result.fail("新增角色失败！");
        }
    }

    /**
     * 修改角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色 id 不能为空！");

            AuthRoleBO authUserBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("RoleController.update.error:{}", e.getMessage());
            return Result.fail("更新角色失败！");
        }
    }

    /**
     * 删除用户
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e){
            log.error("RoleController.delete.error:{}", e.getMessage());
            return Result.fail("删除角色失败！");
        }
    }

}
