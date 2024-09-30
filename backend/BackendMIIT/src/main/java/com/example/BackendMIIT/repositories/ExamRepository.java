package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.models.domain.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository<T, ID> extends BaseRepository<T, ID> {
    List<Exam> findByDirectionName(String directionName);
}
