package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.Service.LopService;
import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.model.ExportPDF;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import net.javaguides.springboot.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LopController {
    @Autowired
    private LopService lopService;
    @Autowired
    private LopRepository lopRepository;
    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/Lop")
    public String showLop(Model model) {

        List<Lop> Lops = new ArrayList<>();
        for (Lop l : lopRepository.findAll())
            Lops.add(l);
        model.addAttribute("List", Lops);
        return "show_lop";
    }

    @GetMapping("/Lop/addLop")
    public String showaddLop(Lop lop, Model model) {

        return "add-lop";
    }

    @PostMapping("/Lop/addLop")
    public String addLop(Lop lop, Model model) {
        lopRepository.save(lop);
        return "redirect:/Lop";
    }

    @PostMapping("/Lop/update/{id}")
    public String updateLop(@PathVariable("id") long id, @Valid Lop lop, BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            lop.setId(id);
            return "update-lop";
        }

        lopRepository.save(lop);

        return "redirect:/Lop";
    }

    @GetMapping("/Lop/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Lop lop = lopRepository.findById(id);


        model.addAttribute("lop", lop);
        return "update-lop";
    }

    @GetMapping("/Lop/delete/{id}")
    public String showDeleteLop(@PathVariable("id") long id, Model model) {
        //neu lop con hoc sinh thi phai xoa het hs roi moi dc xoa lop
        if (lopService.deleteLop(id))
            return "redirect:/Lop";
        else
            return "redirect:/students/list";
    }
}
