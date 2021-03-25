package com.person.norma.basicservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.person.norma.basiccommon.gererator.common.GeneratorContext;
import com.person.norma.basiccommon.gererator.config.DBConfig;
import com.person.norma.basiccommon.gererator.entry.TableInfo;
import com.person.norma.basiccommon.gererator.service.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BasicserviceApplication.class})
public class GeneratorTests {

    @Autowired
    private DBConfig config;

    @Autowired
    private GeneratorService service;

    @Test
    public void contextLoads() {
        ApplicationContext context = GeneratorContext.getApplicationContext();
        config = context.getBean(DBConfig.class);
        service = context.getBean(GeneratorService.class);
        doGenerator("sc_bcst_xml_status", false);
    }

    /**
     * @ Description   :  代码生成
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/23 11:41
     */
    private void doGenerator(String tableName, boolean flag) {
        TableInfo tableInfo = service.queryAllColumns(tableName.toUpperCase(), config);
        String model = tableName.replace("_", "").toLowerCase();
        String comments = tableInfo.getComments();
        AutoGenerator generator = new AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(config.getOutPath());
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(true);
        // xml 二级缓存
        globalConfig.setEnableCache(true);
        // xml resultMap
        globalConfig.setBaseResultMap(true);
        // xml columnList
        globalConfig.setBaseColumnList(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setAuthor(config.getAuthor());

        // 自定义文件命名，注意%s会自动填充表实体属性
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        generator.setGlobalConfig(globalConfig);

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(config.getUrl().contains("mysql") ? DbType.MYSQL : DbType.ORACLE);
        dataSourceConfig.setDriverName(config.getDriver());
        dataSourceConfig.setUsername(config.getUsername());
        dataSourceConfig.setPassword(config.getPassword());
        dataSourceConfig.setUrl(config.getUrl());
        generator.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        if (flag
                && tableName.contains("_")
                && tableName.lastIndexOf("_") != tableName.length() - 1) {
            String prefix = tableName.substring(0, tableName.indexOf("_") + 1);
            // 此处可以修改为您的表前缀
            strategyConfig.setTablePrefix(prefix);
            // 表名生成策略
            NamingStrategy.removePrefix(tableName, prefix);
        } else {
            // 默认表名生成策略
            strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        }
        // 需要生成的表
        strategyConfig.setInclude(tableName);

        // 排除生成的表
        // strategyConfig.setExclude(tableName);

        // 字段名生成策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);

        // 自定义实体公共字段
        // strategyConfig.setSuperEntityColumns("createTime", "updateTime");

        // 自定义 mapper 父类
        // strategyConfig.setSuperMapperClass("com.person.norma");

        // 自定义 service 父类
        // strategyConfig.setSuperServiceClass("com.person.norma");

        // 自定义 serviceImpl 父类
        // strategyConfig.setSuperServiceImplClass("com.person.norma");

        // 自定义 controller 父类
        // strategyConfig.setSuperControllerClass("com.person.norma");

        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);

        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        strategyConfig.setEntityBuilderModel(true);
        generator.setStrategy(strategyConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(config.getPackagePath());
        packageConfig.setModuleName(model);
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("dao");
        packageConfig.setXml("resource");
        packageConfig.setEntity("entity");
        generator.setPackageInfo(packageConfig);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("swaggerControllerNote", comments);
                map.put("entityLombokModel", true);
                map.put("restControllerStyle", true);
                this.setMap(map);
            }
        };
        generator.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/gen-template/controller.java.vm");
        tc.setEntity("/templates/gen-template/entity.java.vm");
        tc.setMapper("/templates/gen-template/dao.java.vm");
        tc.setXml("/templates/gen-template/dao.xml.vm");
        tc.setService("/templates/gen-template/handler.java.vm");
        tc.setServiceImpl("/templates/gen-template/handlerImpl.java.vm");
        generator.setTemplate(tc);
        // 执行生成
        generator.execute();
    }

}
