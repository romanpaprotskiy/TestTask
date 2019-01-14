package com.kindgeek.testtask.service;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import com.kindgeek.testtask.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person getByName(String name) {
        return personRepository.findByNameLike(name);
    }

    @Override
    public Person delete(long id) {
        return personRepository.deleteById(id);
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.updateById(person.getId(),person.getName(),person.getEmail(),person.getPhoneNumber(),
                person.getPosition(),person.getProject());
    }

    @Override
    public Project getProject(long id) {
        return personRepository.findById(id).getProject();
    }

    @Override
    public Position getPosition(long id) {
        return personRepository.findById(id).getPosition();
    }
}
