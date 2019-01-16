package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameLike(String name);

    Person findById(long id);

    Person deleteById(long id);

}