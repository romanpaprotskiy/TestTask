package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId){
        return projectService.getById(projectId);
    }

    @PostMapping
    public Project addProject(@Valid @RequestBody Project project) {
        return projectService.add(project);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @Valid @RequestBody Project project) {
        return projectService.update(projectId, project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        if (projectService.getById(projectId) != null) {
            projectService.delete(projectId);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("ProjectId " + projectId + " not found");
    }

    @GetMapping("/{projectId}/persons")
    public List<Person> getPersons(@PathVariable Long projectId) {
        return projectService.getPersons(projectId);
    }

    @PutMapping("/{projectId}/persons/{personId}")
    public Project addPerson(@PathVariable Long projectId, @PathVariable Long personId) {
        return projectService.addPerson(projectId, personId);
    }

    @DeleteMapping("/{projectId}/persons/{personId}")
    public Project removePerson(@PathVariable Long projectId, @PathVariable Long personId) {
        return projectService.removePerson(projectId, personId);
    }

}
