package com.liuzh10.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liuzh10.domain.SysUser;
import com.liuzh10.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("the username can not be empty");
        }

        LambdaQueryWrapper<SysUser> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = userMapper.selectOne(userQueryWrapper);

        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException(String.format("the %s is not exit", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // TODO cache
        List<String> roleCodeList = userMapper.getRoleCodeByUserName(username);

        roleCodeList.forEach(roleCode -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleCode);
            authorities.add(simpleGrantedAuthority);
        });

        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
