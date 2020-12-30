package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LopController {
    static int count= 1;
   @Autowired
     private LopRepository lopRepository;

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
}
