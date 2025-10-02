package com.don.auth.infra.basic.service;

import com.don.auth.infra.basic.entity.AuthUser;

/**
 * 用户表(AuthUser)表服务接口
 *
 * @author makejava
 * @since 2025-09-21 18:41:02
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询数量
     * @param authUser
     * @return
     */
    Integer queryByConditionCount(AuthUser authUser);

    AuthUser queryByCondition(AuthUser authUser);
}
