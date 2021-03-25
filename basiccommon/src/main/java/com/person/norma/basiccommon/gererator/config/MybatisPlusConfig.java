package com.person.norma.basiccommon.gererator.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @Author： norma
 * @Description：mybatisPlus配置
 * @Date：Create in 16:55 2020/12/21
 * @Modified By：
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.person.norma.**.dao")
@EnableCaching
public class MybatisPlusConfig {

    /**
     * sequence主键，需要配置一个主键生成器
     *
     * @return
     */
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType(DbType.ORACLE.getDb());
        return page;
    }

    /**
     * pageHelper 分页插件
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", DbType.ORACLE.getDb());
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}