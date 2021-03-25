package com.person.norma.basiccommon.gererator.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.person.norma.basiccommon.core.Query;

import java.util.List;

/**
 * @Author： norma
 * @Description：service 父类
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
public interface AbstractCrudService<T> extends IService<T> {

    /**
     * 获取当前DAO
     *
     * @return BaseMapper<T>
     **/
    BaseMapper<T> getDao();

    /**
     * 按查询条件分页获取数据列表
     *
     * @return IPage<Map < String, Object>>
     **/
    IPage<T> listPageByQuery(Query query);

    /**
     * 简单查询
     * select * from tablename where columnName = val
     *
     * @param columnName columnName
     * @param val        value
     * @return T
     * @Author： norma
     * @Date： 2019-6-20 11:59:08
     */
    T selectOneByColumnName(String columnName, Object val);

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
    List<T> selectByColumnName(String columnName, Object val);
}
