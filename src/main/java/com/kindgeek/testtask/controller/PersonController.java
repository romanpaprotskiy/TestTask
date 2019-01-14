package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }


    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personService.getPersons();
    }

    @PostMapping("/addPerson")
    public Person addPerson(@Valid @RequestBody Person person){
        return personService.add(person);
    }



}
