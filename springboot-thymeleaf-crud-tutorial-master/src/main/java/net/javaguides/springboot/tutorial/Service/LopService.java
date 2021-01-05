package net.javaguides.springboot.tutorial.Service;

import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import net.javaguides.springboot.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LopService {
    @Autowired private LopRepository lopRepository;
    @Autowired private StudentRepository studentRepository;

    public boolean deleteLop(long id){
        int count =0;
        boolean kq=true;
        for(Student st : studentRepository.findAll()){
            if(st.getLop().getId()==id){
                count++;
               kq= false;
            }
        }
        Lop l = lopRepository.findById(id);
        if(count==0)
            lopRepository.delete(l);
        return kq;
    }
}
