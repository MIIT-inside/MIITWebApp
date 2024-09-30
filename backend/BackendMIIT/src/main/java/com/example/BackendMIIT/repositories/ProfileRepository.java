package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.models.domain.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends BaseRepository<Profile> {
    List<Profile> findByPassPoint(int passPoint);
    Profile findByName(String name);
}
