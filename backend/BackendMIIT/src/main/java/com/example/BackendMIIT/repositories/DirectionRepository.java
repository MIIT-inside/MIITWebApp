package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    Optional<Direction> findByName(String name);

    Optional<Direction> findByCode(String code);
}
