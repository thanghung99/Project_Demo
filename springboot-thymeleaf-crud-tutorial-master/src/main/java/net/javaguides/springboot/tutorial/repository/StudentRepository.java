package net.javaguides.springboot.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.tutorial.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , QuerydslPredicateExecutor<Student>{
    List<Student> findByName(String name);

}
