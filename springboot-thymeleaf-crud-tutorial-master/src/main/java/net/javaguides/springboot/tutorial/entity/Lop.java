package net.javaguides.springboot.tutorial.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name_lop")
    private String nameLop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lop", fetch = FetchType.LAZY)
    private List<Student> s = new ArrayList<>();



}
