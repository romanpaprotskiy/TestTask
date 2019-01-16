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

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id){
        return personService.getById(id);
    }

    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {
        return personService.add(person);
    }

    @PutMapping("/{personId}")
    public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person person) {
       return personService.update(personId,person);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        if (personService.getById(personId) != null) {
            personService.delete(personId);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("personId " + personId + " not found");
        }
    }

    @GetMapping("/{personId}/project")
    public Project getProject(@PathVariable Long personId){
        return personService.getProject(personId);
    }

    @GetMapping("/{personId}/position")
    public Position getPosition(@PathVariable Long personId){
        return personService.getPosition(personId);
    }

}
