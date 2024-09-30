package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.models.domain.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    List<Direction> findByPassPoint(int passPoint);
    Direction findByName(String name);
}
