package com.lin_q.debursement_api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lin_q.debursement_api.entity.Debursement;
import com.lowagie.text.DocumentException;

public interface PDFGeneratorService {
    public void disbursementSheetExport(Debursement disbursement, HttpServletResponse response) throws DocumentException, IOException;
}
