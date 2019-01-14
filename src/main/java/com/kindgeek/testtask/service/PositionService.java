package com.kindgeek.testtask.service;



import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositions();

    Position getById(long id);

    Position getByName(String name);

    void delete(long id);

    Position add(Position person);

    Position update(long id,Position person);

    Department getDepartment();

    List<Person> getPersons();
}
