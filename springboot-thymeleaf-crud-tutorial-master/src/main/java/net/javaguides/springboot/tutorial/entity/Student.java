package net.javaguides.springboot.tutorial.entity;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;


    @Column(name = "phone_no")
    private long phoneNo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lop")
    private Lop lop;

public  void a(){
    
}




}