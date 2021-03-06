package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LopRepository extends JpaRepository<Lop,Long> {

  Lop findById (long id);
}
