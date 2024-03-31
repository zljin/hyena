package com.zljin.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zljin.auth.entity.SysUser;
import com.zljin.auth.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    SysUserMapper sysUserMapper;

    public UserService(com.zljin.auth.mapper.SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("username", username));
        if (CollectionUtil.isEmpty(sysUsers) || sysUsers.get(0) == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return sysUsers.get(0);
    }

}
