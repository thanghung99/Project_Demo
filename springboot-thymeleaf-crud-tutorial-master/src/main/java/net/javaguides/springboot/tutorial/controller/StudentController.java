package net.javaguides.springboot.tutorial.controller;

import javax.validation.Valid;

import net.javaguides.springboot.tutorial.Service.LopService;
import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students/")
public class StudentController {

	private final StudentRepository studentRepository;

//	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Autowired
	private  LopRepository lopRepository;
	@Autowired private LopService lopService;
	@GetMapping("signup")
	public String showSignUpForm(Student student,Model model) {

		List<Lop> Lops = new ArrayList<>();
		for(Lop l : lopRepository.findAll())
			Lops.add(l);
		model.addAttribute("List",Lops);
		return "add-student";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}


	/*@Valid String id*/
	@PostMapping("add")
	public String addStudent(@Valid Student student,BindingResult result, Model model) {

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

		for(Lop l : lopRepository.findAll())
			if(student.getLop().getId()!=l.getId())
			Lops.add(l);
		model.addAttribute("List",Lops);
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
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		studentRepository.delete(student);
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}
}
