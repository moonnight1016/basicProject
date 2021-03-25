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

package com.person.norma.basiccommon.gererator.service.impl;

import com.person.norma.basiccommon.gererator.config.DBConfig;
import com.person.norma.basiccommon.gererator.dao.GeneratorDao;
import com.person.norma.basiccommon.gererator.entry.TableInfo;
import com.person.norma.basiccommon.gererator.service.GeneratorService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author： norma
 * @Description：代码生成接口实现类
 * @Date：Create in 16:51 2020/12/21
 * @Modified By：
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource(name = "generatorDaoImpl")
    private GeneratorDao dao;

    /**
     * @ Description   :  根据表名查询列信息
     * @ Author        :  norma
     * @ CreateDate    :  2020/12/21 16:39
     */
    @Override
    public TableInfo queryAllColumns(String tableName, DBConfig config) {
        return dao.queryAllColumns(tableName, config);
    }
}
