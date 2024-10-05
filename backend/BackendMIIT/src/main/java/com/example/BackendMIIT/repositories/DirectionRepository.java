package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    Direction findByName(String name);

    Direction findByCode(String code);
}
