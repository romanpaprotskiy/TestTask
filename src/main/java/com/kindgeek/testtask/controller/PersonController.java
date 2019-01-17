package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.PersonService;
import com.kindgeek.testtask.service.PositionService;
import com.kindgeek.testtask.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;
    private PositionService positionService;
    private ProjectService projectService;

    @Autowired
    public PersonController(PersonService personService, PositionService positionService, ProjectService projectService) {
        this.personService = personService;
        this.positionService = positionService;
        this.projectService = projectService;
    }


    /**
     * @param name - search by name like
     * @return all persons in database if name is present - return person with name like
     */
    @GetMapping
    public List<Person> getAllPersons(@RequestParam(required = false) String name) {
        if (name != null){
            return personService.getByName(name);
        }
        return personService.getPersons();
    }

    /**
     * @param id - person id
     * @return person by id
     * @throws ResourceNotFoundException - if id person not found
     */
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getById(id);
    }


    /**
     * @param person - person object which will be added
     * @param positionId - if person object does not have position you can set id position
     * @param projectId - if person object does not have project you can set id project
     * @return person what was added
     */
    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person,@RequestParam(name = "position",required = false) Long positionId,
                            @RequestParam(name = "project",required = false) Long projectId) {
        if (person.getPosition() == null && positionId != null){
            person.addPosition(positionService.getById(positionId));
        }
        Person add = personService.add(person);
        if (person.getProject() == null && projectId != null){
            projectService.addPerson(projectId,add.getId());
        }
        return personService.getById(add.getId());
    }

    /**
     * @param personId - id person
     * @param person   - request person object
     * @param positionId - if is given set position by id
     * @param projectId - if is given set project by id
     * @return - person that was updated in database
     */
    @PutMapping("/{personId}")
    public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person person,
                               @RequestParam(name = "position",required = false) Long positionId,
                               @RequestParam(name = "project",required = false) Long projectId) {

        if (positionId != null) person.addPosition(positionService.getById(positionId));
        if(projectId != null) {
            projectService.addPerson(projectId,personId);
        }
        return personService.update(personId, person);
    }

    /**
     * @param personId - id person which will be deleted
     * @return - 200OK if person was deleted
     * @throws ResourceNotFoundException - if id person not found
     */
    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        if (personService.getById(personId) != null) {
            personService.delete(personId);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("personId " + personId + " not found");
        }
    }

    /**
     * @param personId - id person
     * @return person project
     * @throws ResourceNotFoundException if person id not found
     */
    @GetMapping("/{personId}/project")
    public Project getProject(@PathVariable Long personId) {
        return personService.getProject(personId);
    }

    /**
     * @param personId - id person
     * @return person position
     * @throws ResourceNotFoundException if person id not found
     */
    @GetMapping("/{personId}/position")
    public Position getPosition(@PathVariable Long personId) {
        return personService.getPosition(personId);
    }

    /**
     * @param personId   - id person
     * @param positionId - id position
     * @return person with added position
     * @throws ResourceNotFoundException if personId or positionId not found
     */
    @PutMapping("/{personId}/position")
    public Person addPositionToPerson(@PathVariable Long personId, @RequestParam(name = "position") Long positionId) {
        return personService.addPosition(personId, positionId);
    }

    /**
     * @param personId   - id person
     * @param positionId - id position
     * @return person with added position
     * @throws ResourceNotFoundException if personId or positionId not found
     */
    @DeleteMapping("/{personId}/position")
    public Person removePositionFromPerson(@PathVariable Long personId, @RequestParam(name = "position") Long positionId) {
        return personService.removePosition(personId, positionId);
    }

}
