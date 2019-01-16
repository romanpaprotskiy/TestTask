package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    Person getById(Long id);

    List<Person> getByName(String name);

    void delete(Long id);

    Person add(Person person);

    Person update(Long id,Person person);

    Project getProject(Long id);

    Position getPosition(Long id);

}
