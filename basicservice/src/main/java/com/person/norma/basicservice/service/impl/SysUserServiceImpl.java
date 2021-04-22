package com.person.norma.basicservice.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.person.norma.basiccommon.core.BusinessException;
import com.person.norma.basiccommon.core.Query;
import com.person.norma.basiccommon.gererator.common.AbstractCrudServiceImpl;
import com.person.norma.basicservice.dao.SysUserDao;
import com.person.norma.basicservice.entity.SysUserEntity;
import com.person.norma.basicservice.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author： norma
 * @Description： 用户信息表 服务实现类
 * @Date： 2021-04-22
 * @Modified By：
 */
@Slf4j
@Service
public class SysUserServiceImpl extends AbstractCrudServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUserDao getDao() {
        return sysUserDao;
    }

    /**
     * 按查询条件分页获取数据列表的方法覆盖，添加自定义的条件查询
     *
     * @return IPage<Map   <   String   ,       Object>>
     **/
    @Override
    public IPage<SysUserEntity> listPageByQuery(Query pageQuery) {
        Page<SysUserEntity> page = new Page<>(pageQuery.getPage(), pageQuery.getLimit());
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        // TO ADD CUSTOM QUERY CONDITIONS
        // queryWrapper.eq(FieldName, pageQuery.get(paramName).toString());
        IPage<SysUserEntity> listIPage = this.page(page, queryWrapper);
        return listIPage;
    }
}
