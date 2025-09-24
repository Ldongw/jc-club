package com.don.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2025-09-24 17:28:08
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -87062343294101138L;
/**
     * 主键
     */
    private Long id;
/**
     * 角色id
     */
    private Long roleId;
/**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIdList;

}

