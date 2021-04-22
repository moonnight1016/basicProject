/*
 * (C) Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *   norma
 */

package com.person.norma.basicservice.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.person.norma.basiccommon.core.BusinessException;
import com.person.norma.basicservice.dao.SysUserDao;
import com.person.norma.basicservice.entity.SysUserEntity;
import com.person.norma.basicservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Map<String, Object> doLogin(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        map.put("valid_code", 1);
        List<SysUserEntity> userInfos = sysUserDao.selectByMap(map);
        if (CollectionUtil.isEmpty( userInfos) || userInfos.size() != 1) {
            throw new BusinessException("用户信息异常");
        }

        // 生成 token

        // 存到 redis

        // 设置用户信息
        resultMap.put("token", "tokenValue");
        resultMap.put("expireTime", System.currentTimeMillis());
        resultMap.put("userInfo", userInfos.get(0));
        return resultMap;
    }
}
