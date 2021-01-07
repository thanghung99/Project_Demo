package net.javaguides.springboot.tutorial.Service;

import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import net.javaguides.springboot.tutorial.entity.QStudent;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.repository.LopRepository;
import net.javaguides.springboot.tutorial.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class StudentService {
    @Autowired private LopRepository lopRepository;
    @Autowired private StudentRepository studentRepository;
    @PersistenceContext protected EntityManager entityManager;
    private final int LIMIT =2;
    public  void createCSVFile() throws IOException{

        try {
            FileWriter writer = new FileWriter("Student.csv");
            CSVPrinter printer=CSVFormat.DEFAULT.withHeader("ID","NAME","EMAIL","PHONE","LOP").print(writer);

            for(Student s : studentRepository.findAll())
                 printer.printRecord(s.getId(),s.getName(),s.getEmail(),s.getPhoneNo(),s.getLop().getNameLop());
            printer.flush();
            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public List<Student> getAllStudents(int number){
        String sql = "select * from Student";
        Query query = entityManager.createNativeQuery(sql,Student.class);
        query.setFirstResult((number-1)*LIMIT);
        query.setMaxResults(LIMIT);
        return query.getResultList();
    }
    public void search(long id, String name,String email,long phone){
        QStudent qStudent = QStudent.student;
        JPAQuery<Student> query = new JPAQuery(entityManager);
        JPAQuery<Student> from = query.from(qStudent);
        if (!StringUtils.isEmpty(name)) {
            from.where(qStudent.name.contains(name));
        }
        JPAQuery<Student> studentJPAQuery = from.where(qStudent.id.eq(id));
        studentJPAQuery.fetch();
        String sql =" select * from Student where";
    }
}
