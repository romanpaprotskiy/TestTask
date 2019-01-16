package com.kindgeek.testtask.service;



import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositions();

    Position getById(Long id);

    Position getByName(String name);

    void delete(Long id);

    Position add(Position person);

    Position update(Long id,Position person);

    Department getDepartment(Long id);

    Person getPersons(Long id);
}