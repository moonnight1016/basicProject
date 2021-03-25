package com.person.norma.basiccommon.gererator.dao;

import com.person.norma.basiccommon.gererator.config.DBConfig;
import com.person.norma.basiccommon.gererator.entry.TableInfo;

/**
 * @Author： norma
 * @Description：代码生成dao接口类
 * @Date：Create in 16:53 2020/12/21
 * @Modified By：
 */
public interface GeneratorDao {

    /**
     * @ Description   :  根据表名查询列信息
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/21 16:54
     */
    TableInfo queryAllColumns(String tableName, DBConfig config);
}
