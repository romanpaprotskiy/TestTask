package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    List<Person> getPersons();

    Person getById(long id);

    Person getByName(String name);

    Person delete(long id);

    Person add(Person person);

    Person update(long id,Person person);

    Project getProject();

    Position getPosition();


}
