package umg.edu.devsolutions.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import umg.edu.devsolutions.entity.InventoryReport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static ByteArrayInputStream exportToExcel(List<InventoryReport> inventoryReports) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Inventory Report");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"SKU", "Nombre", "Cantidad", "Costo Unitario", "Precio de Venta"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Fill data rows
            int rowNum = 1;
            for (InventoryReport report : inventoryReports) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(report.getSku());
                row.createCell(1).setCellValue(report.getNombre());
                row.createCell(2).setCellValue(report.getCantidad());
                row.createCell(3).setCellValue(report.getCostoUnitario().doubleValue());
                row.createCell(4).setCellValue(report.getPrecioVenta().doubleValue());
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        }
    }
}
