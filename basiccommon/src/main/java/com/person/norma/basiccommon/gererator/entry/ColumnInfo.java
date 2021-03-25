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

/**
 * @Author： norma
 * @Description：表列的信息
 * @Date：Create in 15:32 2020/12/21
 * @Modified By：
 */
public class ColumnInfo implements Serializable {

    private static final Long serialVersionId = 1L;

    private String columnName;

    private String columnType;

    private String columnComments;

    private String columnExtra;

    public ColumnInfo() {
    }

    public static ColumnInfo build() {
        return new ColumnInfo();
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumnComments() {
        return columnComments;
    }

    public String getColumnExtra() {
        return columnExtra;
    }

    public ColumnInfo setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public ColumnInfo setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public ColumnInfo setColumnComments(String columnComments) {
        this.columnComments = columnComments;
        return this;
    }

    public ColumnInfo setColumnExtra(String columnExtra) {
        this.columnExtra = columnExtra;
        return this;
    }
}
