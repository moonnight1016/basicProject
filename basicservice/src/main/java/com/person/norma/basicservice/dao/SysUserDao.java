package com.person.norma.basicservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.norma.basicservice.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author： norma
 * @Description： 用户信息表 Mapper 接口
 * @Date： 2021-04-22
 * @Modified By：
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

}
