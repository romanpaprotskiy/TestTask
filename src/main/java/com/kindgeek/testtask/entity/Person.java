package com.kindgeek.testtask.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "Person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "Email")
    @Email
    private String email;

    @Column(name = "phoneNumber")
    @Pattern(regexp = "^(((\\+380)?)([0-9]{9}))$")
    private String phoneNumber;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;
    
    public Person(){

    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    public void addPosition(Position position) {
        this.position = position;
        position.setPerson(this);
    }

    public void removePosition(Position position) {
        if (position != null) {
            position.setPerson(null);
        }
        this.position = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) &&
                name.equals(person.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
