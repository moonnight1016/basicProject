/*
 * (C) Copyright 2019-2021 the original author or authors.
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

package com.person.norma.basiccommon.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： norma
 * @Description：Druid监控
 * @Date：Create in 11:21 2021/2/19
 * @Modified By：
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Configuration
public class DruidDBConfig {

    /**
     * @ Description   :  注册ServletRegistrationBean
     * @ Author        :  norma
     * @ CreateDate    :  2021/2/19 11:40
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");
        // 白名单
        registrationBean.addInitParameter("allow", "");
        return registrationBean;
    }

    /**
     * @ Description   :  注册FilterRegistrationBean
     * @ Author        :  norma
     * @ CreateDate    :  2021/2/19 14:38
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.addUrlPatterns("/");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registrationBean.addInitParameter("profileEnable", "true");
        registrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        registrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        registrationBean.addInitParameter("DruidWebStatFilter", "/*");
        return registrationBean;
    }
}
