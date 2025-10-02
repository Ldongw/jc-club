package com.don.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.don.auth.application.convert.AuthUserDTOConverter;
import com.don.auth.application.dto.AuthUserDTO;
import com.don.auth.common.entity.Result;
import com.don.auth.domain.entity.AuthUserBO;
import com.don.auth.domain.service.AuthUserDomainService;
import com.don.auth.infra.basic.entity.AuthUser;
import com.don.auth.infra.basic.mapper.AuthUserDao;
import com.don.auth.infra.basic.service.AuthUserRoleService;
import com.don.auth.infra.basic.service.AuthUserService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private AuthUserDomainService authUserDomainService;

    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        }catch (Exception e){
            log.error("UserController.register.error:{}", e.getMessage());
            return Result.fail("注册用户失败！");
        }
    }

    /**
     * 修改用户信息
     * @param authUserDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("UserController.update.error:{}", e.getMessage());
            return Result.fail("更新用户失败！");
        }
    }

    /**
     * 删除用户
     * @param authUserDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        }catch (Exception e){
            log.error("UserController.delete.error:{}", e.getMessage());
            return Result.fail("删除用户失败！");
        }
    }

    /**
     * 获取用户信息
     * @param authUserDTO
     * @return
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        try{
//            if(log.isInfoEnabled()){
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
//            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空！");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        }catch (Exception e){
            log.error("UserController.getUserInfo.error:{}", e.getMessage());
            return Result.fail("更新用户失败！");
        }
    }


    /**
     * 用户退出
     * @param userName
     * @return
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam String userName){
        try{
            log.info("UserController.logOut.dto:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空！");

            StpUtil.logout(userName);
            return Result.ok();
        }catch (Exception e){
            log.error("UserController.logOut.error:{}", e.getMessage());
            return Result.fail("用户登出失败！");
        }
    }

    /**
     * 角色启用/禁用
     * @param authUserDTO
     * @return
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }

            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("UserController.changeStatus.error:{}", e.getMessage());
            return Result.fail("启用/禁用用户失败！");
        }
    }
    private static void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空！");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮件地址不能为空！");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "用户密码不能为空！");
    }

    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空！");
            return Result.ok(authUserDomainService.doLogin(validCode));
        }catch (Exception e){
            log.error("UserController.changeStatus.error:{}", e.getMessage());
            return Result.fail("用户登录失败！");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
    
}