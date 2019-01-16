package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Position;

import java.util.List;


public interface DepartmentService {

    List<Department> getDepartments();

    Department getById(Long id);

    Department getByName(String name);

    void delete(Long id);

    Department add(Department department);

    Department update(Long id,Department department);

    List<Position> getPositions(Long id);

    Department addPosition(Long id,Long positionId);

    Department removePosition(Long id,Long positionId);
}
