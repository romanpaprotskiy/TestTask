package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + " not found"));
    }

    @Override
    public Department getByName(String name) {
        return departmentRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department add(Department department) {
        return null;
    }

    @Override
    public Department update(Long id, Department department) {
        return null;
    }

    @Override
    public List<Position> getPositions() {
        return null;
    }
}
