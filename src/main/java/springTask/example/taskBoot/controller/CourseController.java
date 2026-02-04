package springTask.example.taskBoot.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.*;
import springTask.example.taskBoot.Service.ReportService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import springTask.example.taskBoot.entities.Course;
import springTask.example.taskBoot.util.utilPdfGenerator;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController{
    @Autowired
    private ReportService reportService;

    @GetMapping("/excel")
   public void generateExcelReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");

        response.setHeader("Content-Disposition","attachment;filename=courses.xls");
        reportService.generateExcel(response);
   }

   @GetMapping("/pdf")
   public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment ; filename=courses.pdf");

        List<Course> list=reportService.getAllCourses();
       utilPdfGenerator.pdfGenerator(list,response);
   }
   @PostMapping("/courses")
   public Course saveCourse(@RequestBody Course course){
     return reportService.saveCourse(course);
   }
}
