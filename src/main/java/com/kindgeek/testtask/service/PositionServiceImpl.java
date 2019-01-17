package com.kindgeek.testtask.service;

import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.repository.DepartmentRepository;
import com.kindgeek.testtask.repository.PersonRepository;
import com.kindgeek.testtask.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, PersonRepository personRepository, DepartmentRepository departmentRepository) {
        this.positionRepository = positionRepository;
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Position getById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("positionId " + id + " not found"));
    }

    @Override
    public List<Position> getByName(String name) {
        return positionRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Position add(Position position) {

        Department department = departmentRepository.findById(position.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department  not found"));

        department.addPosition(position);

        Person person = position.getPerson() != null ? personRepository.findById(position.getPerson().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found")) : null;

        if (person != null) person.addPosition(position);

        return positionRepository.save(position);
    }

    @Override
    public Position update(Long id, Position position) {

        Position position1 = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position " + id + " not found"));

        Person person = position.getPerson() != null ? personRepository.findById(position.getPerson().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found")) : null;

        Department department = departmentRepository.findById(position.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        position1.setName(position.getName());
        if (person != null) person.addPosition(position1);
        return positionRepository.save(position1);
    }

    @Override
    public Department getDepartment(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position " + id + " not found"))
                .getDepartment();
    }

    @Override
    public Person getPerson(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position " + id + " not found"))
                .getPerson();
    }
}
