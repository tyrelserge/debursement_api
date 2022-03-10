package com.lin_q.debursement_api.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lin_q.debursement_api.model.DebursementExcelData;
import com.lin_q.debursement_api.repository.DebursementRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    //private static Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DebursementRepository debursementRepository;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override    
    public List<DebursementExcelData> getDisbursementsByPeriod(String sectorId, String from, String to) {
        List<Object[]> debs = !sectorId.equalsIgnoreCase("all") ? debursementRepository.fetchDisbursementsByPeriodAndBgIndex(sectorId, from, to)
            : debursementRepository.fetchDisbursementsByPeriod(from, to);
            return debs.stream().map(mapper->{
                DebursementExcelData deb = new DebursementExcelData(mapper);
                return deb;
            }).collect(Collectors.toList());
    }

    @Override
    public ByteArrayInputStream disbursementReportByPeriod(List<DebursementExcelData> disbursementList, String sheetName) {
        
        try(Workbook workbook = new XSSFWorkbook()) {
            
            Sheet sheet = workbook.createSheet(sheetName);
            Row headerRow = sheet.createRow(0);
            

            // Define header font style

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
        

            // Define header cell style

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setFont(headerFont);


            // Creating header cells 

            Cell cell = headerRow.createCell(0);
            cell.setCellValue("Désignation");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(1);
            cell.setCellValue("Affectation");
            cell.setCellStyle(headerCellStyle);
    
            cell = headerRow.createCell(2);
            cell.setCellValue("Bénéficiaire");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(3);
            cell.setCellValue("N° Pièces");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(4);
            cell.setCellValue("Date");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(5);
            cell.setCellValue("Sortie");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each field
            
            for(int i = 0; i < disbursementList.size(); i++) {
                String username = disbursementList.get(i).getCivility() + ". " + disbursementList.get(i).getFirstname() + " " + disbursementList.get(i).getLastname();
                String sectorAndBudget = disbursementList.get(i).getBudgsectorName() +" - "+ disbursementList.get(i).getGroupedbudgetName();
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(disbursementList.get(i).getReason());
                dataRow.createCell(1).setCellValue(sectorAndBudget);                                    // Sector + GroupBuget
                dataRow.createCell(2).setCellValue(username);
                dataRow.createCell(3).setCellValue(disbursementList.get(i).getIdentifier());            // 2203/A001
                dataRow.createCell(4).setCellValue(disbursementList.get(i).getCreatedOn());             // Créer or Validate date ???
                dataRow.createCell(5).setCellValue(disbursementList.get(i).getAmountApproved());
                username = null;
            }
    
            // Size of column auto resize to fit with data

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
            
        } catch (IOException ex) {
            return null;
        }

    }

    
}
