package com.kindgeek.testtask.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "position_name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "position",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Person> persons;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    public List<Person> getPerson() {
        return persons;
    }

    public void setPerson(List<Person> persons) {
        this.persons = persons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
