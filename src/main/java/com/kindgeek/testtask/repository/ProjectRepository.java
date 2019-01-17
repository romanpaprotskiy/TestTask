package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Position p where p.name like %:name%")
    Project findByNameLike(String name);


}
