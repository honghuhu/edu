package org.online.edu.service.impl;

import lombok.AllArgsConstructor;
import org.online.edu.entity.SecurityUser;
import org.online.edu.entity.User;
import org.online.edu.service.PermissionService;
import org.online.edu.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author 镜子白
 * @since 2019-11-08
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByUsername(username);
        // 判断用户是否存在
        if (null == user) {
            //throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(user);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
