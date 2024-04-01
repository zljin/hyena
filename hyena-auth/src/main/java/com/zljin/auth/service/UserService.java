package com.zljin.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zljin.auth.entity.SysRole;
import com.zljin.auth.entity.SysUser;
import com.zljin.auth.entity.UserRoles;
import com.zljin.auth.mapper.SysRoleMapper;
import com.zljin.auth.mapper.SysUserMapper;
import com.zljin.auth.mapper.UserRolesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    SysUserMapper sysUserMapper;

    UserRolesMapper userRolesMapper;

    SysRoleMapper sysRoleMapper;

    public UserService(SysUserMapper sysUserMapper, UserRolesMapper userRolesMapper, SysRoleMapper sysRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.userRolesMapper = userRolesMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("username", username));
        if (CollectionUtil.isEmpty(sysUsers) || sysUsers.get(0) == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        SysUser sysUser = sysUsers.get(0);
        List<UserRoles> userRoles = userRolesMapper.selectList(new QueryWrapper<UserRoles>().eq("user_id", sysUser.getUserId()));
        List<SysRole> sysRoles = new ArrayList<>();
        userRoles.forEach(userRoles1 -> {
            SysRole sysRole = new SysRole();
            sysRole.setRoleCode(userRoles1.getRolesId() == 1 ? "ROLE_ADMIN" : "ROLE_NORMAL");
            sysRoles.add(sysRole);
        });
        sysUser.setSysRoles(sysRoles);
        return sysUser;
    }

}
