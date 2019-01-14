package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByNameLike(String name);

    @Modifying
    @Query("update Person p set p.name = :name,p.email = :email,p.phoneNumber = :phone," +
            "p.position = :position,p.project = :project where p.id = :id")
    Person updateById(Long id, String name, String email, String phone, Position position, Project project);

}