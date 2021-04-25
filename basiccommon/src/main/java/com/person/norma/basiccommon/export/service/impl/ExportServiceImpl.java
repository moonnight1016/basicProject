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

package com.person.norma.basiccommon.export.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.person.norma.basiccommon.core.BusinessException;
import com.person.norma.basiccommon.export.service.ExportService;
import com.person.norma.basiccommon.export.vo.ExportCustomColumnsVO;
import com.person.norma.basiccommon.export.vo.ExportParamsVO;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExportServiceImpl implements ExportService {

    private static final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";

    @Override
    public void exportByCustomColumns(ExportParamsVO param, HttpServletResponse response) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            List<ExportCustomColumnsVO> customColumnsVOS = param.getCloumnsVOS();
            List<Map<String, Object>> dataVOS = param.getBusiDatas();

            if (CollectionUtil.isEmpty(customColumnsVOS)) {
                throw new BusinessException("自定义列信息不可为空!");
            }
            XSSFSheet sheet = workbook.createSheet(param.getSheetName());

            // 标题样式
            XSSFFont titleFont = workbook.createFont();
            titleFont.setFontName(param.getFontName());
            titleFont.setFontHeightInPoints((short) param.getFontPoints());
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleStyle.setLocked(true);

            // 正文样式
            XSSFFont nalFont = workbook.createFont();
            nalFont.setFontName(param.getContentFontName());
            nalFont.setFontHeightInPoints((short) param.getContentFontPoints());
            XSSFCellStyle nalStyle = workbook.createCellStyle();
            nalStyle.setFont(nalFont);
            nalStyle.setAlignment(HorizontalAlignment.CENTER);
            nalStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            nalStyle.setLocked(true);

            // sheet 设置列宽
            List<Integer> colWidths = customColumnsVOS.stream()
                    .map(ExportCustomColumnsVO::getWidth)
                    .collect(Collectors.toList());
            for (int i = 0; i < colWidths.size(); i++) {
                sheet.setColumnWidth(i, colWidths.get(i));
            }

            // sheet 设置第一行为表头标题
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell cell;
            for (int i = 0; i < customColumnsVOS.size() - 1; i++) {
                cell = titleRow.createCell(i);
                cell.setCellStyle(nalStyle);
                saveCellValue(cell, customColumnsVOS.get(i).getLabel());
            }

            // 填写数据
            if (CollectionUtil.isNotEmpty(dataVOS)) {
                XSSFRow nalRow;
                int rowNum = 1;
                for (Map<String, Object> map : dataVOS) {
                    nalRow = sheet.createRow(rowNum);
                    int cellNum = 0;
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        cell = nalRow.createCell(cellNum);
                        saveCellValue(cell, entry.getValue());
                        cellNum++;
                    }
                    rowNum++;
                }
            }

            // 设置respose
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + param.getExcelName() + ".xlsx");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
                workbook = null;
            }
        }
    }

    /**
     * @ Description   :  单元格赋值
     * @ Author        :  norma
     * @ CreateDate    :  2021/4/23 21:50
     */
    private void saveCellValue(XSSFCell cell, Object value) {
        if (null == value) {
            cell.setCellValue("");
        } else {
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Long) {
                cell.setCellValue(formatNumber((Long) value, "#0"));
            } else if (value instanceof Double) {
                cell.setCellValue(formatNumber((Double) value, "#0.00"));
            } else if (value instanceof Float) {
                cell.setCellValue(formatNumber((Float) value, "#0.00"));
            } else if (value instanceof Integer) {
                cell.setCellValue(formatNumber((Integer) value, "#0"));
            } else if (value instanceof BigDecimal) {
                cell.setCellValue(formatNumber((BigDecimal) value, "#0.00"));
            } else if (value instanceof java.sql.Date) {
                cell.setCellValue(new SimpleDateFormat(DATE_FORMAT).format(value));
            } else if (value instanceof java.util.Date) {
                cell.setCellValue(new SimpleDateFormat(DATE_FORMAT).format(value));
            } else {
                cell.setCellValue("");
            }
        }
    }

    /**
     * @ Description   :  number格式化
     * @ Author        :  norma
     * @ CreateDate    :  2021/4/23 23:32
     */
    private String formatNumber(Number number, String forMat) {
        if (null == number) {

            //当传入的number 为Null 返回不带 #号的格式
            return forMat.replace("#", "").replace(".+", ".");
        } else {

            //number格式化 替换多个#为 一个# ,替换 多个. 为一个.
            return new DecimalFormat(("#" + forMat).replace("#+", "#").replace(".+", ".")).format(number.doubleValue());
        }
    }

}
