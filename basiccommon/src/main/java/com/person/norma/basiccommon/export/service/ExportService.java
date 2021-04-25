package com.person.norma.basiccommon.export.service;

import com.person.norma.basiccommon.export.vo.ExportParamsVO;

import javax.servlet.http.HttpServletResponse;

public interface ExportService {

    void exportByCustomColumns(ExportParamsVO param, HttpServletResponse response);
}
