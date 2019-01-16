package com.kindgeek.testtask.service;



import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjects();

    Project getById(Long id);

    Project getByName(String name);

    void delete(Long id);

    Project add(Project project);

    Project update(Long id,Project project);

    List<Person> getPersons(Long id);

    Project addPerson(Long id,Long personId);

    Project removePerson(Long id,Long personId);
}
