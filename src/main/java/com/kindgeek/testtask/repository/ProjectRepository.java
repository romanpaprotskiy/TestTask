package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p where p.name like %:name%")
    List<Project> findByNameLike(String name);


}
