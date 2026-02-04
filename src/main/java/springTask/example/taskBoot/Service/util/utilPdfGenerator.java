package springTask.example.taskBoot.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import springTask.example.taskBoot.entities.Course;

import javax.print.attribute.standard.DocumentName;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.itextpdf.text.Document;

public class utilPdfGenerator {
    public static void pdfGenerator(List<Course> list, HttpServletResponse response) throws IOException, DocumentException {

  Document document=new Document();
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        PdfPTable table=new PdfPTable(3);

        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Price");

        for(Course c:list){
            table.addCell(String.valueOf(c.getcId()));
            table.addCell(c.getcName());
            table.addCell(String.valueOf(c.getPrice()));

        }

        document.add(table);
        document.close();
    }
}
