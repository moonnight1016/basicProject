package com.person.norma.basiccommon.gererator.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.norma.basiccommon.core.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Author： norma
 * @Description：serviceImpl 父类
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
public abstract class AbstractCrudServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements AbstractCrudService<T> {

    /**
     * 获取当前DAO
     *
     * @return M
     */
    @Override
    public abstract M getDao();

    @Override
    public IPage<T> listPageByQuery(Query query) {
        Page<T> page = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // TO ADD QUERY CONDITIONS
        IPage<T> listIPage = this.page(page, queryWrapper);
        return listIPage;
    }

    /**
     * 简单查询
     * select * from tablename where columnName = val
     *
     * @param columnName columnName
     * @param val        value
     * @return T
     * @Author： norma
     * @Date： 2019-06-17
     */
    @Override
    public T selectOneByColumnName(String columnName, Object val) {
        if (StringUtils.isBlank(columnName) || null == val) {
            return null;
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName.trim(), val);
        List<T> list = getDao().selectList(queryWrapper);

        if (CollectionUtils.isEmpty(list) || 1 != list.size()) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 简单查询
     * select * from tablename where columnName = val
     *
     * @param columnName columnName
     * @param val        value
     * @return T
     * @Author： norma
     * @Date： 2019-7-16 16:14:16
     */
    @Override
    public List<T> selectByColumnName(String columnName, Object val) {
        if (StringUtils.isBlank(columnName) || null == val) {
            return null;
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName.trim(), val);

        List<T> list = getDao().selectList(queryWrapper);
        return list == null ? Collections.emptyList() : list;
    }
}
