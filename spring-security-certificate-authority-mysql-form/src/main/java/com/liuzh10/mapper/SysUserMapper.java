package com.liuzh10.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuzh10.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {


    List<String> getRoleCodeByUserName(String username);
}
