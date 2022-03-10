package com.lin_q.debursement_api.service;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

import com.lin_q.debursement_api.model.DebursementExcelData;

public interface ExcelExportService {

    public List<DebursementExcelData> getDisbursementsByPeriod(String sectorId, String from, String to) throws ParseException;
    
    public ByteArrayInputStream disbursementReportByPeriod(List<DebursementExcelData> disbursementList, String sheetname);

}
