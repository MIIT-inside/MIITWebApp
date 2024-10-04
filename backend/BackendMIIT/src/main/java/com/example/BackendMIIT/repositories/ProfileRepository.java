package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends BaseRepository<Profile> {
    Profile findByName(String name);
}
