package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.repository.DepartmentRepository;
import com.kindgeek.testtask.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

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
    public List<Department> getByName(String name) {
        return departmentRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Long id, Department department) {

        Department department1 = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + " not found"));

        department1.setName(department.getName());
        return departmentRepository.save(department1);
    }

    @Override
    public List<Position> getPositions(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + " not found"))
                .getPositions();
    }

    @Override
    public Department addPosition(Long id, Long positionId) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + " not found"));

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        department.addPosition(position);
        return departmentRepository.save(department);
    }

    @Override
    public Department removePosition(Long id, Long positionId) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + " not found"));

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        department.removePosition(position);
        return departmentRepository.save(department);
    }

}
