package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByNameLike(String name);

    @Query("update Project p set p.name = :name,p.description = :description where p.id = :id")
    Project updateById(long id, String name, String description);

}
