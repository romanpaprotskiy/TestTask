package com.kindgeek.testtask.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "departmentReference")
    private List<Position> positions;

    public void addPosition(Position position){
        positions.add(position);
        position.setDepartment(this);
    }

    public void removePosition(Position position){
        positions.remove(position);
        position.setDepartment(null);
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

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
