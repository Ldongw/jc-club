package com.don.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(AuthRole)实体类dto
 *
 * @author makejava
 * @since 2025-09-22 10:55:46
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -31574465518998395L;
/**
     * 主键
     */
    private Long id;
/**
     * 角色名称
     */
    private String roleName;
/**
     * 唯一标识
     */
    private String roleKey;

}

