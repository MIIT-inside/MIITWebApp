package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {

    Optional<Direction> findByName(String name);

    Optional<Direction> findByCode(String code);

    @Query("SELECT d FROM Direction d LEFT JOIN d.passPoints p ORDER BY p.avg DESC, d.name ASC")
    Page<Direction> findAllOrderByAvgPassPointAndName(Pageable pageable);

    @Query("SELECT d FROM Direction d LEFT JOIN d.passPoints p ORDER BY p.min DESC, d.name ASC")
    Page<Direction> findAllOrderByMinPassPointAndName(Pageable pageable);

    @Query("SELECT d FROM Direction d ORDER BY d.name ASC")
    Page<Direction> findAllOrderByName(Pageable pageable);
}

