package com.kindgeek.testtask.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "project_description")
    @Size(max = 255)
    private String description;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Person> persons;

    public void addPerson(Person person){
        persons.add(person);
        person.setProject(this);
    }

    public void removePerson(Person person){
        person.setProject(null);
        this.persons.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id) &&
                name.equals(project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
