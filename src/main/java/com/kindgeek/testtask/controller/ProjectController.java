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

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * @param name - search by name
     * @return all project or if name is present return all by like name
     */
    @GetMapping
    public List<Project> getProjects(@RequestParam(required = false) String name) {
        if (name != null)
            return projectService.getByName(name);
        return projectService.getProjects();
    }

    /**
     * @param projectId - id project
     * @return project by id
     */
    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId){
        return projectService.getById(projectId);
    }

    /**
     * @param project - request person object
     * @return project which was added
     */
    @PostMapping
    public Project addProject(@Valid @RequestBody Project project) {
        return projectService.add(project);
    }

    /**
     * @param projectId - id project
     * @param project - request project object
     * @return project which was updated
     */
    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @Valid @RequestBody Project project) {
        return projectService.update(projectId, project);
    }

    /**
     * @param projectId - id project
     * @return 200ok if project was deleted
     * @throws ResourceNotFoundException if project not found
     */
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        if (projectService.getById(projectId) != null) {
            projectService.delete(projectId);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("ProjectId " + projectId + " not found");
    }

    /**
     * @param projectId - id project
     * @return all persons which work on this project
     */
    @GetMapping("/{projectId}/persons")
    public List<Person> getPersons(@PathVariable Long projectId) {
        return projectService.getPersons(projectId);
    }

    /**
     * @param projectId id project
     * @param personId id person
     * @return project with added person
     */
    @PutMapping("/{projectId}/persons")
    public Project addPerson(@PathVariable Long projectId, @RequestParam(name = "person") Long personId) {
        return projectService.addPerson(projectId, personId);
    }

    /**
     * @param projectId id project
     * @param personId id person
     * @return project without person
     */
    @DeleteMapping("/{projectId}/persons")
    public Project removePerson(@PathVariable Long projectId, @RequestParam(name = "person") Long personId) {
        return projectService.removePerson(projectId, personId);
    }

}
