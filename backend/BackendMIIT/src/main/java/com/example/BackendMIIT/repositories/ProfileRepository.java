package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends BaseRepository<Profile> {
    List<Profile> findByPassPoint(int passPoint);

    Optional<Profile> findByName(String name);
}
