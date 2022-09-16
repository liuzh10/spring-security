package com.liuzh10.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuzh10.domain.SysUser;
import com.liuzh10.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> {

    /**
     * findUserByAccount
     *
     * @param account account
     * @return User
     */
    public SysUser findUserByAccount(String account) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_account", account);
        queryWrapper.eq("data_status", "1");
        return this.baseMapper.selectOne(queryWrapper);
    }
}
