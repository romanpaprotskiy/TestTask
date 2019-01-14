package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    Person getById(long id);

    Person getByName(String name);

    Person delete(long id);

    Person add(Person person);

    Person update(Person person);

    Project getProject(long id);

    Position getPosition(long id);


}
