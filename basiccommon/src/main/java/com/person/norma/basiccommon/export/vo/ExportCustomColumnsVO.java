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

package com.person.norma.basiccommon.export.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "导出接口-自定义列")
public class ExportCustomColumnsVO {

    @ApiModelProperty(value = "标题")
    private String label;

    @ApiModelProperty(value = "属性")
    private String prop;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "列宽度")
    private int width = 50;
}
