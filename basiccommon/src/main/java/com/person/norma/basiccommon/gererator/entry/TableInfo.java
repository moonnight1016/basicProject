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

package com.person.norma.basiccommon.gererator.entry;

import java.io.Serializable;
import java.util.List;

/**
 * @Author： norma
 * @Description：表信息
 * @Date：Create in 15:30 2020/12/21
 * @Modified By：
 */
public class TableInfo implements Serializable {

    private static final long serialVersionId = 1L;

    private String tableName;

    private String comments;

    private List<ColumnInfo> columnInfos;

    public TableInfo() {
    }

    public static TableInfo build() {
        return new TableInfo();
    }

    public String getTableName() {
        return tableName;
    }

    public String getComments() {
        return comments;
    }

    public List<ColumnInfo> getColumnInfos() {
        return columnInfos;
    }

    public TableInfo setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public TableInfo setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public TableInfo setColumnInfos(List<ColumnInfo> columnInfos) {
        this.columnInfos = columnInfos;
        return this;
    }
}
