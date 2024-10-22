package umg.edu.devsolutions.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import umg.edu.devsolutions.entity.InventoryReport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PdfExporter {

    public static ByteArrayInputStream exportToPdf(List<InventoryReport> inventoryReports) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(18);
        Paragraph mainTitle = new Paragraph("DevSolutions", titleFont);
        mainTitle.setAlignment(Element.ALIGN_CENTER);
        mainTitle.setSpacingAfter(5); // Reduce space after the title
        document.add(mainTitle);

        Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA);
        subtitleFont.setSize(14);
        Paragraph subtitle = new Paragraph("Reporte de Inventario", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 3, 1, 2, 2});

        Stream.of("SKU", "Nombre", "Cantidad", "Costo Unitario", "Precio de Venta")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setPhrase(new Phrase(headerTitle));
                    table.addCell(header);
                });

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