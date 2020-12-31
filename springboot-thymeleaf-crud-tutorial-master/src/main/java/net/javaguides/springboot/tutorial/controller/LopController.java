package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.entity.Student;
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
    static int count= 1;
   @Autowired
     private LopRepository lopRepository;
    @Autowired private StudentRepository studentRepository;
@GetMapping("/Lop")
    public String showLop(Model model){
    if(count==1) {
        Lop l1 = new Lop();

        l1.setNameLop("A");

        Lop l2 = new Lop();

        l2.setNameLop("B");


        lopRepository.save(l1);
        lopRepository.save(l2);
        count++;
    }
    List<Lop> Lops = new ArrayList<>();
    for(Lop l : lopRepository.findAll())
        Lops.add(l);
    model.addAttribute("List",Lops);
        return "show_lop";
    }
    @GetMapping("/Lop/addLop")
    public String showaddLop(Lop lop,Model model){

        return "add-lop";
    }
    @PostMapping("/Lop/addLop")
    public String addLop(Lop lop , Model model){
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
    @PostMapping("/Lop/delete/{id}")
    public String deletelop(@PathVariable("id") long id,Model model){
//    int count =0;
//        for(Student st : studentRepository.findAll()){
//            if(st.getLop().getId()==id){
//                count++;
//                return "redirect:/Student/list";
//            }
//        }

            Lop l = lopRepository.findById(id);
      //          if(count==0)
                  lopRepository.delete(l);

    return "redirect:/Lop";
    }
    @GetMapping("/Lop/delete/{id}")
    public String showDeleteLop(@PathVariable("id") long id,Model model){
        int count =0;
        for(Student st : studentRepository.findAll()){
            if(st.getLop().getId()==id){
                count++;
                return "redirect:/Student/list";
            }
        }
    
        Lop l = lopRepository.findById(id);
                 if(count==0)
        lopRepository.delete(l);

        return "redirect:/Lop";
    }
}
