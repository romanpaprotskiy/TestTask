package com.kindgeek.testtask.service;



import com.kindgeek.testtask.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjects();

    Project getById(long id);

    Project getByName(String name);

    void delete(long id);

    Project add(Project project);

    Project update(long id,Project project);


}
