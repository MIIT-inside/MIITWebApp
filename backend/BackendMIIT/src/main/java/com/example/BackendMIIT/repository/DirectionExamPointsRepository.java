package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.DirectionExamPoints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionExamPointsRepository extends BaseRepository<DirectionExamPoints> {
    List<DirectionExamPoints> findByDirection(Direction direction);
}
