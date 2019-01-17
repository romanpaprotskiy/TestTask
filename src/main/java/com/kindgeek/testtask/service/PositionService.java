package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositions();

    Position getById(Long id);

    List<Position> getByName(String name);

    void delete(Long id);

    Position add(Position person);

    Position update(Long id, Position position);

    Department getDepartment(Long id);

    Person getPerson(Long id);
}
