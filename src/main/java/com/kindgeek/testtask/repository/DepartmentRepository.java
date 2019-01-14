package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Department findByNameLike(String name);

    Department findById(long id);

    Department deleteById(long id);

}
