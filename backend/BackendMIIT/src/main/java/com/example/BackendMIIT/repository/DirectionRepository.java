package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    Direction findByName(String name);

    Direction findByCode(String code);
}
