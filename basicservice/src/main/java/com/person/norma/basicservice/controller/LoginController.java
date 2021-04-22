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

package com.person.norma.basicservice.controller;

import com.person.norma.basiccommon.core.Result;
import com.person.norma.basicservice.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "登录控制层", tags = {"登录-操作接口"})
@RestController
@RequestMapping("/sys/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    protected LoginService getService() {
        return loginService;
    }

    @ApiOperation(value = "登录接口", notes = "登录-登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_name", paramType = "query", dataType = "String", value = "用户账号", required = true),
            @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "密码", required = true)
    })
    @GetMapping(value = "/doLogin")
    public Result doLogin(@ApiParam(hidden = true) @RequestParam Map<String, Object> paramMap) {
        return Result.ok(getService().doLogin(paramMap));
    }
}
