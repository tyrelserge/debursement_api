package com.lin_q.debursement_api.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lin_q.debursement_api.Util;
import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BudgetService budgetService;

    @Override
    public void disbursementSheetExport(Debursement disbursement, HttpServletResponse response) 
        throws DocumentException, IOException {
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(14);
        Paragraph title = new Paragraph("SORTIE DE CAISSE", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(6);         // 6 column
        table.setWidthPercentage(100);
        table.setSpacingBefore(40);

        writeTableData(table, disbursement);        

        document.add(title);
        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, Debursement disbursement) {

        List<ReasonItems> reasons = disbursement.getReasonItems();
        List<ValidationAction> actions = disbursement.getValidations();
        User claimant = userService.getUserData(disbursement.getUserId());
        User recipient = userService.getUserData(disbursement.getRecipientId());
        BudgetIndex index = budgetService.getBudgetIndex(disbursement.getBudgindexId());
        GroupedBudget budget = budgetService.getGroupedBudget(index.getGroupedbudgetId());
        BudgetarySector sector = budgetService.getBudgetarySector(index.getBudgsectorId());

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10); 
        
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorderWidth(0);

        Font fontAccent = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font important = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        important.setColor(Color.RED);
        
        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
        cell.setBorderColor(Color.LIGHT_GRAY);

        // Line 1 & 2

        cell.setPaddingBottom(20);
        cell.setColspan(3);
        cell.setPhrase(new Phrase("Décaissement N° : " + String.valueOf(disbursement.getIdentifier()), fontAccent));
        table.addCell(cell);
        
        cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
        cell.setColspan(3);
        cell.setPhrase(new Phrase("Date : " + new SimpleDateFormat("dd/MM/yyyy").format(disbursement.getCreatedOn())));
        table.addCell(cell);

        cell.setPaddingBottom(0);
        cell.setPhrase(new Phrase());
        table.addCell(cell);

        cell.setPaddingBottom(5);
        cell.setPhrase(new Phrase("Emetteur : " + String.valueOf(
            claimant.getCivility() + ". " +claimant.getFirstname() + " " + claimant.getLastname())));
        table.addCell(cell);

        // Ligne 3 & 4        

        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);

        cell.setColspan(0);
        cell.setPhrase(new Phrase());
        table.addCell(cell);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Bénéficiaire : "));
        table.addCell(cell);

        cell.setColspan(3);
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPhrase(new Phrase(String.valueOf(
            recipient.getCivility() + ". " +recipient.getFirstname() + " " + recipient.getLastname())));
        table.addCell(cell);

        cell.setBackgroundColor(Color.WHITE);
        cell.setColspan(6);
        cell.setPhrase(new Phrase());
        table.addCell(cell);

        cell.setColspan(0);
        cell.setPhrase(new Phrase());
        table.addCell(cell);
        
        cell.setColspan(2);
        cell.setPhrase(new Phrase("Montant demandé : "));
        table.addCell(cell);

        cell.setColspan(3);
        cell.setPhrase(new Phrase(Util.thousandSeparator(disbursement.getAmountRequested()) + " XOF"));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(cell);

        cell.setBackgroundColor(Color.WHITE);

        // Séparator 
        
        cell.setColspan(6);
        cell.setBorderWidthBottom(1);
        cell.setPhrase(new Phrase());
        table.addCell(cell);

        cell.setPaddingBottom(10);
        cell.setBorderWidthBottom(0);
        
        // Line 5, 6 7, 8

        cell.setColspan(6);
        cell.setPaddingBottom(20);
        cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        cell.setPhrase(new Phrase(String.valueOf(
            sector.getBudgsectorName() +" - " + budget.getGroupedbudgetName()), fontAccent));
        table.addCell(cell);

        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Indice budgétaire : "));
        table.addCell(cell);

        cell.setColspan(4);
        cell.setPhrase(new Phrase(String.valueOf(index.getBudgindexName())));
        table.addCell(cell);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Motif : "));
        table.addCell(cell);
        
        cell.setColspan(4);
        cell.setPhrase(new Phrase(String.valueOf(disbursement.getReason())));
        table.addCell(cell);

        cell.setPadding(5);
        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);

        cell.setColspan(3);
        cell.setPhrase(new Phrase("Désignation", fontAccent));
        table.addCell(cell);

        cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        cell.setColspan(0);
        cell.setPhrase(new Phrase("Qté", fontAccent));
        table.addCell(cell);
        
        cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);

        cell.setPhrase(new Phrase("Prix Unitaire", fontAccent));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Prix Total", fontAccent));
        table.addCell(cell);

        cell.setColspan(6);        
        cell.setBorderWidthBottom(1);
        cell.setPhrase(new Phrase()); table.addCell(cell);

        cell.setBorderWidthBottom(0);

        for(ReasonItems reason: reasons) {
            cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
            cell.setColspan(3);
            cell.setPhrase(new Phrase(String.valueOf(reason.getDesignation())));
            table.addCell(cell);

            cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            cell.setColspan(0);
            cell.setPhrase(new Phrase(String.valueOf(reason.getQuatity())));
            table.addCell(cell);

            cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPhrase(new Phrase(Util.thousandSeparator(reason.getUnitprice())));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(Util.thousandSeparator(reason.getTotalprice())));
            table.addCell(cell);
        }
        
        cell.setColspan(6);        
        cell.setBorderWidthBottom(1);
        cell.setPhrase(new Phrase()); table.addCell(cell);
        
        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
        cell.setBorderWidthBottom(0);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Total :", fontAccent));
        table.addCell(cell);

        cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);

        cell.setColspan(4);
        cell.setPhrase(new Phrase(String.valueOf(Util.thousandSeparator(disbursement.getAmountRequested()) + " XOF"), fontAccent));
        table.addCell(cell);

        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Montant Approuvé : ", fontAccent));
        table.addCell(cell);

        String approuved = disbursement.getAmountApproved().toString().isBlank() ? "-" : 
            Util.thousandSeparator(disbursement.getAmountApproved()) + " XOF";

        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setColspan(4);
        cell.setPhrase(new Phrase(String.valueOf(approuved), fontAccent));

        if(disbursement.getStatus().equalsIgnoreCase("rejected")) {
            approuved = "REFUSE";
            cell.setPhrase(new Phrase(String.valueOf(approuved), important));
        }

        table.addCell(cell);

        cell.setBackgroundColor(Color.WHITE);

        cell.setColspan(2);
        cell.setPhrase(new Phrase());
        table.addCell(cell);

        cell.setColspan(4);
        cell.setPhrase(new Phrase((disbursement.getPayment()!=null ? disbursement.getPayment() + " - " : "") 
            + "Le " + new SimpleDateFormat("dd/MM/yyyy").format(disbursement.getUpdatedOn())));
        table.addCell(cell);

        // Validation line
        
        cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

        cell.setColspan(6);        
        cell.setBorderWidthBottom(1);
        cell.setPhrase(new Phrase()); table.addCell(cell);
        
        cell.setColspan(6);        
        cell.setBorderWidthBottom(0);
        cell.setPhrase(new Phrase()); table.addCell(cell);

        
        cell.setColspan(6);        
        cell.setPhrase(new Phrase("Validateurs", fontAccent)); table.addCell(cell);

        cell.setBorderWidth(1);  
        cell.setBorderWidthBottom(1);

        for(ValidationAction action: actions) {
            User validator = userService.getUserData(action.getUserId());
            cell.setColspan(2);        
            cell.setPhrase(new Phrase(
                validator.getCivility() + ". " +validator.getLastname() + " " + validator.getFirstname())
            ); table.addCell(cell);
        }

        cell.setBorderWidth(0); 
        cell.setBorderWidthBottom(0);
        cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);

        cell.setColspan(6);              
        cell.setPhrase(new Phrase()); table.addCell(cell);

        cell.setPaddingTop(40);
        cell.setColspan(6); 
        cell.setPhrase(new Phrase("Siganture du bénéficiaire")); table.addCell(cell);
    }
    
}
