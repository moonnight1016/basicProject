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

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "导出接口-入参")
public class ExportParamsVO {

    @ApiModelProperty(value = "分页名称")
    private String sheetName = "sheet1";

    @ApiModelProperty(value = "标题字体")
    private String fontName = "宋体";

    @ApiModelProperty(value = "标题字体大小")
    private int fontPoints = 14;

    @ApiModelProperty(value = "正文字体")
    private String contentFontName = "Consolas";

    @ApiModelProperty(value = "正文字体大小")
    private int contentFontPoints = 15;

    @ApiModelProperty(value = "自定义属性列")
    private List<ExportCustomColumnsVO> cloumnsVOS;

    @ApiModelProperty(value = "业务数据")
    private List<Map<String, Object>> busiDatas;
}
