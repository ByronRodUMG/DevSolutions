package umg.edu.devsolutions.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import umg.edu.devsolutions.entity.InventoryReport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PdfExporter {

    public static ByteArrayInputStream exportToPdf(List<InventoryReport> inventoryReports) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        // Add title
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph title = new Paragraph("Inventory Report", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        // Create table
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 3, 1, 2, 2});

        // Add table headers
        Stream.of("SKU", "Nombre", "Cantidad", "Costo Unitario", "Precio de Venta")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setPhrase(new Phrase(headerTitle));
                    table.addCell(header);
                });

        // Add data rows
        for (InventoryReport report : inventoryReports) {
            table.addCell(report.getSku());
            table.addCell(report.getNombre());
            table.addCell(String.valueOf(report.getCantidad()));
            table.addCell(String.valueOf(report.getCostoUnitario()));
            table.addCell(String.valueOf(report.getPrecioVenta()));
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
