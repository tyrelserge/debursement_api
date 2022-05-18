package com.lin_q.debursement_api.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.model.DebursementExcelData;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.service.DisbursementService;
import com.lin_q.debursement_api.service.ExcelExportService;
import com.lin_q.debursement_api.service.PDFGeneratorService;
import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping({"/export"})
public class ExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private DisbursementService disbursementService;

    //@Autowired
    private final PDFGeneratorService pdfGeneratorService;

    public ExportController(PDFGeneratorService _pdfGeneratorService) {
        this.pdfGeneratorService = _pdfGeneratorService;
    }

    @GetMapping("/sector/{sectorId}/disbursement/{from}/{to}")
    public ResponseEntity<ResponseDto<List<DebursementExcelData>>> DisbursementReportByPeriod(HttpServletResponse httpServResponse,
        @PathVariable("sectorId") String sectorId, @PathVariable("from") String from, @PathVariable("to") String to) throws IOException, ParseException {
        List<DebursementExcelData> disbursementList = excelExportService.getDisbursementsByPeriod(sectorId, from, to);
        return disbursementList!=null && !disbursementList.isEmpty() ? ResponseEntity.ok(new ResponseDto<List<DebursementExcelData>>(Constants.SUCCESS, disbursementList)) : 
                ResponseEntity.ok(new ResponseDto<List<DebursementExcelData>>(Constants.ERROR, null));
    }

    @GetMapping("/sector/{sectorId}/disbursement/{from}/{to}/treated")
    public ResponseEntity<ResponseDto<List<DebursementExcelData>>> TreatedDisbursementReportByPeriod(HttpServletResponse httpServResponse,
        @PathVariable("sectorId") String sectorId, @PathVariable("from") String from, @PathVariable("to") String to) throws IOException, ParseException {
        List<DebursementExcelData> disbursementList = excelExportService.getTreatedDisbursementsByPeriod(sectorId, from, to);
        return disbursementList!=null && !disbursementList.isEmpty() ? ResponseEntity.ok(new ResponseDto<List<DebursementExcelData>>(Constants.SUCCESS, disbursementList)) : 
                ResponseEntity.ok(new ResponseDto<List<DebursementExcelData>>(Constants.ERROR, null));
    }

    @GetMapping("/sector/{sectorId}/disbursement/{from}/{to}/to-excel")
    public ResponseEntity<InputStreamResource> ExportDisbursementReportByPeriod(HttpServletResponse httpServResponse,
        @PathVariable("sectorId") String sectorId, @PathVariable("from") String from, @PathVariable("to") String to) throws IOException, ParseException {

        List<DebursementExcelData> disbursementList = excelExportService.getDisbursementsByPeriod(sectorId, from, to);

        from = from.replace('/', '-');
        to = to.replace('/', '-');
        
        String interval = !from.equals(to) ? "du_" + from +"_au_" + to : "du_" + to;
        String sheetName = "Point_" + interval + ".xlsx";
        ByteArrayInputStream bais = excelExportService.disbursementReportByPeriod(disbursementList, sheetName);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename="+ sheetName);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
        // ou
        //httpServResponse.setContentType("application/octet-stream");
        //httpServResponse.setHeader("Content-Disposition", "attachement; filename="+ sheetName);
        //IOUtils.copy(bais, httpServResponse.getOutputStream());
    }

    
    @GetMapping("/sector/{sectorId}/disbursement/{from}/{to}/treated/to-excel")
    public ResponseEntity<InputStreamResource> ExportTreatedDisbursementReportByPeriod(HttpServletResponse httpServResponse,
        @PathVariable("sectorId") String sectorId, @PathVariable("from") String from, @PathVariable("to") String to) throws IOException, ParseException {

        List<DebursementExcelData> disbursementList = excelExportService.getTreatedDisbursementsByPeriod(sectorId, from, to);

        from = from.replace('/', '-');
        to = to.replace('/', '-');
        
        String interval = !from.equals(to) ? "du_" + from +"_au_" + to : "du_" + to;
        String sheetName = "Point_" + interval + ".xlsx";
        ByteArrayInputStream bais = excelExportService.disbursementReportByPeriod(disbursementList, sheetName);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename="+ sheetName);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }

    
    @GetMapping("/disbursement/{disbursId}/to-pdf")
    public void ExportPDFDailyReport(HttpServletResponse response, @PathVariable("disbursId") Integer disbursId) throws DocumentException, IOException {
        
        response.setContentType("application/pdf");
        String currentDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=" + currentDate + ".pdf";
        response.setHeader(headerKey, headerValue);

        Debursement disbursement = disbursementService.getUserDisbursementRequest(disbursId);
        pdfGeneratorService.disbursementSheetExport(disbursement, response);
    }
    
}
