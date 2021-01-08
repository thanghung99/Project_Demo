package net.javaguides.springboot.tutorial.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.javaguides.springboot.tutorial.service.ExportPDFService;
import net.javaguides.springboot.tutorial.service.LopService;
import net.javaguides.springboot.tutorial.service.StudentService;
import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.model.ApiRespone;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.repository.StudentRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students/")

public class StudentController {


    private static final  Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    private final StudentRepository studentRepository;
    @Autowired
    private ExportPDFService exportPDF;

    //	@Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private LopRepository lopRepository;
    @Autowired
    private LopService lopService;

    @GetMapping("signup")
    public String showSignUpForm(Student student, Model model) {

        List<Lop> Lops = new ArrayList<>();
        for (Lop l : lopRepository.findAll())
            Lops.add(l);
        model.addAttribute("List", Lops);
        return "add-student";
    }

    @PostMapping("api/delete")
    public ResponseEntity<ApiRespone> apiXoaStudent(@RequestBody final Map<String, Object> id) {
        String str = (String) id.get("id");


        studentRepository.deleteById(Long.parseLong(str));

        return ResponseEntity.ok(new ApiRespone(200, "Xoa thanh cong"));
    }


    @GetMapping("list")
    public String showUpdateForm(Model model, HttpServletRequest request) {

        int max = 2;
        for (Student t : studentRepository.findAll())
            max++;
        int pageNumber = 1;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            pageNumber = Integer.valueOf(request.getParameter("page"));
            if (pageNumber < 1) pageNumber = 1;
            if (pageNumber > max / 2) pageNumber = pageNumber - 1;
        }

        model.addAttribute("students", studentService.getAllStudents(pageNumber));
        model.addAttribute("currentPage", pageNumber);
        return "index";
    }

    /////// Export CSV Student
    @GetMapping("exportcsv")
    public String exportCSV() throws IOException {

        studentService.createCSVFile();
		log.info("File successfully saved at the given path.");
        return "redirect:list";
    }

    ////////
    @GetMapping("exportpdf")
    public String exportPDF(HttpServletResponse response) throws JRException {
        try {
            String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "temp.jrxml").getAbsolutePath();

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lopRepository.findAll());
            Map parameters = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
            //createPdfReport(lopRepository.findAll());
            log.info("File successfully saved at the given path.");
        } catch (final Exception e) {
          //  log.error("Some error has occured while preparing the employee pdf report.");
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return "redirect:list";
    }

    /*@Valid String id*/
    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-student";
        }

        student.getLop().getNameLop();

        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        List<Lop> Lops = new ArrayList<>();

        for (Lop l : lopRepository.findAll())
            if (student.getLop().getId() != l.getId())
                Lops.add(l);
        model.addAttribute("List", Lops);
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "add-student";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, Model model) {
        studentRepository.deleteById(Long.parseLong(id));
        return "redirect:/students/list";
    }

    @GetMapping("list/search")
    public String search(Model model, @RequestParam(value = "search") String search) {
        model.addAttribute("students", studentService.search(search));
        model.addAttribute("currentPage", null);
        return "index";
    }
//	private void createPdfReport(final List<Lop> employees) throws JRException {
//		// Fetching the .jrxml file from the resources folder.
//		final InputStream stream = this.getClass().getResourceAsStream("/templates/report.jrxml");
//
//		// Compile the Jasper report from .jrxml to .japser
//		final JasperReport report = JasperCompileManager.compileReport(stream);
//
//		// Fetching the employees from the data source.
//		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
//
//		// Adding the additional parameters to the pdf.
//		final Map<String, Object> parameters = new HashMap<>();
//		parameters.put("createdBy", "javacodegeek.com");
//
//		// Filling the report with the employee data and additional parameters information.
//		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
//
//		// Users can change as per their project requrirements or can take it as request input requirement.
//		// For simplicity, this tutorial will automatically place the file under the "c:" drive.
//		// If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
//		final String filePath = "\\";
//		// Export the report to a PDF file.
//		JasperExportManager.exportReportToPdfFile(print, filePath + "Employee_report.pdf");
//	}


}
