/*
 * (C) Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     norma
 */

package com.person.norma.basiccommon.gererator.dao.impl;

import com.person.norma.basiccommon.gererator.config.DBConfig;
import com.person.norma.basiccommon.gererator.dao.GeneratorDao;
import com.person.norma.basiccommon.gererator.entry.ColumnInfo;
import com.person.norma.basiccommon.gererator.entry.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： norma
 * @Description：代码生成dao接口实现类
 * @Date：Create in 16:55 2020/12/21
 * @Modified By：
 */
@Slf4j
@Repository
public class GeneratorDaoImpl implements GeneratorDao {

    /**
     * @ Description   :  根据表名查询列信息
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/21 16:54
     */
    @Override
    public TableInfo queryAllColumns(String tableName, DBConfig config) {
        TableInfo tableInfo = TableInfo.build()
                .setTableName(tableName);
        Connection conn = connect(config);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String execuSql = "";

            // 得到表注解
            if (config.getUrl().contains("mysql")) {
                execuSql = "select table_comment from information_schema.tables where table_name = '"
                        + tableName
                        + "' and table_schema = '"
                        + config.getSchema()
                        + "'";
            } else {
                execuSql = "select comments from user_tab_comments where table_name = '" + tableName + "'";
            }
            ResultSet resultSet = stmt.executeQuery(execuSql);
            while (resultSet.next()) {
                tableInfo.setComments(resultSet.getString(1));
            }

            // 得到字段注解
            if (config.getUrl().contains("mysql")) {
                execuSql = "select column_name, data_type, column_comment, character_maximum_length, extra from information_schema.columns where table_name = '"
                        + tableName
                        + "' and table_schema = '"
                        + config.getSchema()
                        + "'";
            } else {
                execuSql = "select utc.column_name, utc.data_type, ucc.comments from user_tab_columns utc, user_col_comments ucc where utc.table_name = ucc.table_name and utc.column_name = ucc.column_name and utc.table_name = '" + tableName + "'";
            }
            List<ColumnInfo> columnInfos = new ArrayList<>();
            resultSet = stmt.executeQuery(execuSql);
            while (resultSet.next()) {
                ColumnInfo columnInfo = ColumnInfo.build()
                        .setColumnName(resultSet.getString(1))
                        .setColumnComments(resultSet.getString(3));
                if (config.getUrl().contains("mysql") && "varchar".equalsIgnoreCase(resultSet.getString(2))) {
                    columnInfo.setColumnType(resultSet.getString(2) + "(" + resultSet.getString(4) + ")");
                } else {
                    columnInfo.setColumnType(resultSet.getString(2));
                }

                if (isExistColumn(resultSet, 5) && !StringUtils.isEmpty(resultSet.getString(5))) {
                    columnInfo.setColumnExtra(resultSet.getString(5));
                } else {
                    columnInfo.setColumnExtra("");
                }
                columnInfos.add(columnInfo);
            }
            tableInfo.setColumnInfos(columnInfos);
        } catch (SQLException e) {
            throw new RuntimeException("execute sql occer error", e);
        } finally {
            // 关闭声明
            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tableInfo;
    }

    /**
     * @ Description   :  获取数据库连接
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/21 17:08
     */
    private Connection connect(DBConfig config) {
        Connection conn = null;
        try {
            Class.forName(config.getDriver());
            conn = DriverManager.getConnection(config.getUrl(),
                    config.getUsername(),
                    config.getPassword());
        } catch (Exception e) {
            log.error("数据库连接失败！" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * @ Description   :  判断查询结果集中是否存在指定列
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/22 15:33
     */
    private boolean isExistColumn(ResultSet resultSet, Integer index) {
        try {
            if (null != resultSet.getString(index)) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
