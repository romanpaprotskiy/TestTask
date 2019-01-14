package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Position;

import java.util.List;


public interface DepartmentService {

    List<Department> getDepartments();

    Department getById(long id);

    Department getByName(String name);

    Department delete(long id);

    Department add(Department department);

    Department update(long id,Department department);

    List<Position> getPositions();

}
