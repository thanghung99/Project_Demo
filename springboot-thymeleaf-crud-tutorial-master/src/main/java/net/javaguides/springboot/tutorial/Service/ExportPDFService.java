package net.javaguides.springboot.tutorial.Service;

import net.javaguides.springboot.tutorial.model.SimpleReportExporter;
import net.javaguides.springboot.tutorial.model.SimpleReportFiller;
import net.sf.jasperreports.engine.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Service
public class ExportPDFService {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ResourceLoader resourceLoader;

//    public JasperPrint exportPdfFile() throws SQLException, JRException, IOException {
////        Connection conn = jdbcTemplate.getDataSource().getConnection();
////
////        String path = resourceLoader.getResource("classpath:Purchase.jrxml").getURI().getPath();
////
////        JasperReport jasperReport = JasperCompileManager.compileReport(path);
////
////        // Parameters for report
////        Map<String, Object> parameters = new HashMap<String, Object>();
////
////        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
//
//        return print;
//    }
public void exportPdfFile(){
    SimpleReportFiller simpleReportFiller = new SimpleReportFiller();


    simpleReportFiller.setReportFileName("employeeReport.jrxml");
    simpleReportFiller.compileReport();

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("title", "Employee Report Example");
    parameters.put("minSalary", 15000.0);
    parameters.put("condition", " LAST_NAME ='Smith' ORDER BY FIRST_NAME");

    simpleReportFiller.setParameters(parameters);
    simpleReportFiller.fillReport();

    SimpleReportExporter simpleExporter = new SimpleReportExporter();
    simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());

    simpleExporter.exportToPdf("employeeReport.pdf", "baeldung");
    simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
    simpleExporter.exportToCsv("employeeReport.csv");
    simpleExporter.exportToHtml("employeeReport.html");
}

}
