package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.repository.PersonRepository;
import com.kindgeek.testtask.repository.PositionRepository;
import com.kindgeek.testtask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"));
    }

    @Override
    public List<Person> getByName(String name) {
        return personRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person add(Person person) {

        Position position = positionRepository.findById(person.getPosition().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        person.addPosition(position);

        Project project = person.getProject() != null ? projectRepository.findById(person.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found")) : null;

        if (project != null) project.addPerson(person);

        return personRepository.save(person);
    }

    @Override
    public Person update(Long id, Person person) {

        Position position = positionRepository.findById(person.getPosition().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        Project project = person.getProject() != null ? projectRepository.findById(person.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found")) : null;

        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"));

        person1.setName(person.getName());
        person1.setEmail(person.getEmail());
        person1.setPhoneNumber(person.getPhoneNumber());
        person1.setPosition(position);
        position.setPerson(person1);

        if (person1.getProject() != null && !person1.getProject().equals(project)) {
            Project project1 = projectRepository.findById(person1.getProject().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
            project1.removePerson(person1);
        }

        if (project != null) {
            project.addPerson(person1);
        }

        return personRepository.save(person1);
    }

    @Override
    public Project getProject(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"))
                .getProject();
    }

    @Override
    public Position getPosition(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"))
                .getPosition();
    }

    @Override
    public Person addPosition(Long id,Position position) {

        Position position1 = positionRepository.findById(position.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"));

        person.addPosition(position1);
        return personRepository.save(person);
    }

    @Override
    public Person removePosition(Long id, Position position) {
        Position position1 = positionRepository.findById(position.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonId " + id + " not found"));

        person.removePosition(position1);
        return personRepository.save(person);
    }

}
