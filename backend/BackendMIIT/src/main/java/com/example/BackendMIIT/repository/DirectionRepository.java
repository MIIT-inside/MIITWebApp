package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {
    Optional<Direction> findByName(String name);

    Optional<Direction> findByCode(String code);

    @Query("SELECT d FROM Direction d LEFT JOIN d.passPoints p GROUP BY d ORDER BY p.avg DESC, d.name ASC")
    List<Direction> findAllOrderByAvgPassPointAndName();

    @Query("SELECT d FROM Direction d LEFT JOIN d.passPoints p GROUP BY d ORDER BY p.min DESC, d.name ASC")
    List<Direction> findAllOrderByMinPassPointAndName();
}
