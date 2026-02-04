package springTask.example.taskBoot.Service;

import springTask.example.taskBoot.entities.Course;
import springTask.example.taskBoot.repository.CourseRepository;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            throw new IOException("No courses found to export");
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses info");

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("PRICE");

        int dataRowIndex = 1;
        for (Course course : courses) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(course.getcId());
            dataRow.createCell(1).setCellValue(course.getcName());
            dataRow.createCell(2).setCellValue(course.getPrice());
            dataRowIndex++;
        }

        try (ServletOutputStream ops = response.getOutputStream()) {
            workbook.write(ops);
            ops.flush();
        }

        workbook.close();
    }
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
}
