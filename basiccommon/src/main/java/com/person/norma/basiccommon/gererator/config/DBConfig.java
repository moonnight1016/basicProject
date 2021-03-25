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

package com.person.norma.basiccommon.gererator.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Properties;

/**
 * @Author： norma
 * @Description：数据库及其它配置信息
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
@Configuration
public class DBConfig implements Serializable {

    private static final long serialVersionId = 1L;

    // 数据库配置
    private String dbName;
    private String url;
    private String driver;
    private String username;
    private String password;
    private String schema;
    private String catelog;
    private String dbType;

    //包路径和模块名
    private String packagePath;
    private String moduleName;
    private String author;

    // 文件输出路径
    private String outPath;
    private String outXml;
    private String outBase;

    public DBConfig() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        ClassPathResource resource = new ClassPathResource("generator.yml");
        yaml.setResources(resource);
        Properties properties = yaml.getObject();
        this.setUrl((String) properties.get("gen.db.url"))
                .setUsername((String) properties.get("gen.db.username"))
                .setPassword((String) properties.get("gen.db.password"))
                .setDriver((String) properties.get("gen.db.driver"))
                .setDbType((String) properties.get("gen.db.dbtype"))
                .setPackagePath((String) properties.get("gen.parent"))
                .setModuleName((String) properties.get("gen.modulename"))
                .setAuthor((String) properties.get("gen.author"))
                .setOutPath((String) properties.get("gen.outpath"))
                .setOutXml((String) properties.get("gen.outxml"))
                .setOutBase((String) properties.get("gen.outbase"));
    }

    public String getDbName() {
        return dbName;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchema() {
        return schema;
    }

    public String getCatelog() {
        return catelog;
    }

    public String getDbType() {
        return dbType;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public String getOutPath() {
        return outPath;
    }

    public String getOutXml() {
        return outXml;
    }

    public String getOutBase() {
        return outBase;
    }

    public DBConfig setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public DBConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public DBConfig setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public DBConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public DBConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public DBConfig setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public DBConfig setCatelog(String catelog) {
        this.catelog = catelog;
        return this;
    }

    public DBConfig setDbType(String dbType) {
        this.dbType = dbType;
        return this;
    }

    public DBConfig setPackagePath(String packagePath) {
        this.packagePath = packagePath;
        return this;
    }

    public DBConfig setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public DBConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    public DBConfig setOutPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public DBConfig setOutXml(String outXml) {
        this.outXml = outXml;
        return this;
    }

    public DBConfig setOutBase(String outBase) {
        this.outBase = outBase;
        return this;
    }
}
