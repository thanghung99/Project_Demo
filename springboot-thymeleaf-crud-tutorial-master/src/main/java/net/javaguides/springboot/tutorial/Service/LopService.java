package net.javaguides.springboot.tutorial.Service;

import net.javaguides.springboot.tutorial.entity.Lop;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import net.javaguides.springboot.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LopService {
    @Autowired
    private LopRepository lopRepository;
    @Autowired
    private StudentRepository studentRepository;

    public boolean deleteLop(long id) {

        boolean kq = true;
        for (Student st : studentRepository.findAll()) {
            if (st.getLop().getId() == id) {
                kq = false;
            }
        }
        lopRepository.deleteById(id);
        return kq;
    }
}
