package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.repository.PersonRepository;
import com.kindgeek.testtask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + " not found"));
    }

    @Override
    public Project getByName(String name) {
        return projectRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project add(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {

        Project project1 = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + " not found"));

        project1.setName(project.getName());
        project1.setDescription(project.getDescription());
        return projectRepository.save(project1);
    }

    @Override
    public List<Person> getPersons(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + " not found"))
                .getPersons();
    }

    @Override
    public Project addPerson(Long id, Long personId) {

        Person person1 = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + " not found"));

        project.addPerson(person1);
        return projectRepository.save(project);
    }

    @Override
    public Project removePerson(Long id, Long personId) {
        Person person1 = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + " not found"));

        project.removePerson(person1);
        return projectRepository.save(project);
    }
}
