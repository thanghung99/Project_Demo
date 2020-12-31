package net.javaguides.springboot.tutorial.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name_lop")
    private String nameLop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lop", fetch = FetchType.LAZY)
    private List<Student> s = new ArrayList<>();


    public List<Student> getS() {
        return s;
    }

    public void setS(List<Student> s) {
        this.s = s;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameLop() {
        return nameLop;
    }

    public void setNameLop(String nameLop) {
        this.nameLop = nameLop;
    }
}
