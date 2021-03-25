package com.person.norma.basiccommon.gererator.service;

import com.person.norma.basiccommon.gererator.config.DBConfig;
import com.person.norma.basiccommon.gererator.entry.TableInfo;

/**
 * @Author： norma
 * @Description：代码生成接口类
 * @Date：Create in 15:43 2020/12/21
 * @Modified By：
 */
public interface GeneratorService {

    /**
     * @ Description   :  根据表名查询列信息
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/21 16:39
     */
    TableInfo queryAllColumns(String tableName, DBConfig config);
}
