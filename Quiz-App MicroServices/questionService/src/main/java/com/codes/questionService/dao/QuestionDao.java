package com.codes.questionService.dao;

import com.codes.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    public List<Question> getQuestionByCategory(String category);
    public boolean existsByCategory(String category);

    @Query(value = "SELECT q.id FROM question Q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ" , nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category, Integer numQ);
}
