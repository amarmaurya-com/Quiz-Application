package com.codes.questionService.controller;


import com.codes.questionService.dao.QuestionDao;
import com.codes.questionService.model.Question;
import com.codes.questionService.model.QuestionWrapper;
import com.codes.questionService.payload.Result;
import com.codes.questionService.model.Response;
import com.codes.questionService.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @Autowired
    QuestionDao dao;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<?> getAllQuestions() {
        System.out.println(environment.getProperty("local.server.port"));
        if(dao.findAll().isEmpty())
            return new ResponseEntity<>(new Result(false,"Data is empty",null),HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(service.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{topic}")
    public ResponseEntity<?> getQuestionByCategory(@PathVariable("topic") String category) {
            if (dao.existsByCategory(category)){
                    return new ResponseEntity<>(new Result(true, "Data Found", service.getQuestionByCategory(category)), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Result(false,"Data Not Found",null),HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<Result> addQuestion(@RequestBody Question question) {

        if (service.addQuestion(question)!=null)
            return new ResponseEntity<>(new Result(true, "Data Created", Collections.singletonList(question)), HttpStatus.CREATED);
        return new ResponseEntity<>(new Result(false,"Data Is Empty",null),HttpStatus.NOT_FOUND);
    }

    @PutMapping("update")
    public ResponseEntity<Result> updateQuestion(@RequestBody Question question) {
        if(service.updateQuestion(question)!=null)
            return new ResponseEntity<>(new Result(true,"Question Updated",Collections.singletonList(question)),HttpStatus.CREATED);
        return new ResponseEntity<>(new Result(false,"Data May be having Null Element", null),HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Result> deleteQuestionById(@PathVariable("id") int id) {
        if(service.deleteQuestionById(id) != null)
            return new ResponseEntity<>(new Result(true, "Question Deleted", null), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(new Result(false, "Question Not Found", null), HttpStatus.NOT_FOUND);
    }
    // generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestion) {
        return service.getQuestionForQuiz(categoryName, numQuestion);
    }

    // getQuestion (questionId)
    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return service.getQuestionFormId(questionIds);
    }
    // getScore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return service.getScore(responses);
    }

}
