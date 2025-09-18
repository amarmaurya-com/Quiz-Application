package com.codes.quizService.dao;

import com.codes.quizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
