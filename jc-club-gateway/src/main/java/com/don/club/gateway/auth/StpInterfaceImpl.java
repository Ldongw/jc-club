package com.don.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.don.club.gateway.redis.RedisUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        return getAuth(authPermissionPrefix, loginId.toString());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(authRolePrefix, loginId.toString());
    }

    private List<String> getAuth(String prefix, String loginId) {
        String authKey = redisUtil.buildKey(prefix, loginId);
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue))
            return Collections.emptyList();
        List<String> authList = new Gson().fromJson(authValue, List.class);

        return authList;
    }

}
