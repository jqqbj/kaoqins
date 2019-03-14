package com.rrx.kaoqins.core.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro权限获取
 */
@Slf4j
@Component
public class SysUserRealm extends AuthorizingRealm {


    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //Long userId = WebUtil.getCurrentUserId();
        //List<String> permissionList = sysAuthorizeService.queryPermissionsByUserId(userId);
        List<String> permissionList = new ArrayList<>();
        permissionList.add("sys:dw:list");
        permissionList.stream().forEach(permission -> {
            simpleAuthorizationInfo.addStringPermission(permission);
        });
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        SysUserModel sysUserModel = sysUserService.queryByAccount(usernamePasswordToken.getUsername());
//        if (null == sysUserModel) {
//            throw new UnknownAccountException();
//        }
//        if (!sysUserModel.getPassword().equals(new String(usernamePasswordToken.getPassword()))) {
//            throw new IncorrectCredentialsException();
//        }
//        WebUtil.saveCurrentUser(sysUserModel);
        return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(),usernamePasswordToken.getPassword(), "管理员");
    }
}