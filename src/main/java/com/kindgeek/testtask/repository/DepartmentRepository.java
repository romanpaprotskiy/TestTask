package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query("select p from Position p where p.name like %:name%")
    List<Department> findByNameLike(String name);


}
