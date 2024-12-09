package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Category;
import com.example.BackendMIIT.model.domain.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends BaseRepository<Direction> {

    Optional<Direction> findByName(String name);

    Optional<Direction> findByCode(String code);

    @Query("SELECT d FROM Direction d JOIN d.passPoints pp " +
            "WHERE pp.category = :category AND pp.min <= :totalScore")
    List<Direction> findByMinPassPointsLessThanEqual(@Param("category") Category category,
                                                     @Param("totalScore") int totalScore);
}
