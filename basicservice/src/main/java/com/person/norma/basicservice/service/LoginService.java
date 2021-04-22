package com.person.norma.basicservice.service;

import com.person.norma.basicservice.entity.SysUserEntity;

import java.util.Map;

public interface LoginService {

    /**
    * @ Description   :  登录方法
    * @ Author        :  norma
    * @ CreateDate    :  2021/4/22 23:26
    */
    Map<String, Object> doLogin(Map<String, Object> map);
}
