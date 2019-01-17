package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * @return all persons in database
     */
    @GetMapping
    public List<Person> getAllPersons() {
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
     * @param person - person object that will be added in database
     * @return - person that was added in database
     * @throws ResourceNotFoundException - if person position not found
     */
    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {
        return personService.add(person);
    }

    /**
     * @param personId - id person
     * @param person   - request person object
     * @return - person that was updated in database
     */
    @PutMapping("/{personId}")
    public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person person) {
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
     * @return
     */
    @PutMapping("/{personId}/position/{positionId}")
    public Person addPositionToPerson(@PathVariable Long personId, @PathVariable Long positionId) {
        return personService.addPosition(personId, positionId);
    }

    @DeleteMapping("/{personId}/position/{positionId}")
    public Person removePositionFromPerson(@PathVariable Long personId, @PathVariable Long positionId) {
        return personService.removePosition(personId, positionId);
    }

}
