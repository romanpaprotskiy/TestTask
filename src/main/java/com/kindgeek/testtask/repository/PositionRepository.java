package com.kindgeek.testtask.repository;


import com.kindgeek.testtask.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

    Position findByNameLike(String name);

    Position findById(long id);

    Position deleteById(long id);
}
