package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByNameLike(String name);


}
