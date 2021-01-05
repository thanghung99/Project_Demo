package net.javaguides.springboot.tutorial.Service;

import net.javaguides.springboot.tutorial.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired private LopRepository lopRepository;
    @Autowired private StudentService studentService;


}
