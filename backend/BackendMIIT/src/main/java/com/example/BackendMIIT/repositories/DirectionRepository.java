package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    Direction findByName(String name);
}
