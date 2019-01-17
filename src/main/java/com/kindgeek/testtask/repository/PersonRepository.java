package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.name like %:name%")
    List<Person> findByNameLike(String name);


}