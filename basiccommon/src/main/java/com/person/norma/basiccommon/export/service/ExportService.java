package com.person.norma.basiccommon.export.service;

import com.person.norma.basiccommon.export.vo.ExportParamsVO;

public interface ExportService {

    void exportByCustomColumns(ExportParamsVO param);
}
