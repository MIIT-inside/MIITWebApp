package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends BaseRepository<Profile> {
    Optional<Profile> findByName(String name);
}
