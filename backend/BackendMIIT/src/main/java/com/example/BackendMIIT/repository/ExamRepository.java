package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends BaseRepository<Exam> {
    List<Exam> findByDirectionName(String directionName);
}
