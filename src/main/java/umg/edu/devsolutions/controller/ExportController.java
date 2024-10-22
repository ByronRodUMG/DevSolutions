package umg.edu.devsolutions.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umg.edu.devsolutions.service.InventoryService;
import umg.edu.devsolutions.util.ExcelExporter;
import umg.edu.devsolutions.util.PdfExporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/xlsx")
    public ResponseEntity<InputStreamResource> exportToExcel() throws IOException {
        ByteArrayInputStream in = ExcelExporter.exportToExcel(inventoryService.getInventoryReport());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=inventory_report.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> exportToPdf() throws DocumentException {
        ByteArrayInputStream in = PdfExporter.exportToPdf(inventoryService.getInventoryReport());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=inventory_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
}
