package com.codes.questionService.service;

import com.codes.questionService.dao.QuestionDao;
import com.codes.questionService.model.Question;
import com.codes.questionService.model.QuestionWrapper;
import com.codes.questionService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao dao;

    public List<Question> getAllQuestions() {
    return dao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return dao.getQuestionByCategory(category);
    }

    public Question addQuestion(Question question) {
        return dao.save(question);
    }

    public Question updateQuestion(Question question) {
        return dao.save(question);
    }

    public Question deleteQuestionById(int id) {
        Optional<Question> questionOpt = dao.findById(id);

        if (questionOpt.isPresent()) {
            dao.deleteById(id);
            return questionOpt.get();
        }

        return null;
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        return new ResponseEntity<>(dao.findRandomQuestionByCategory(categoryName,numQuestion), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFormId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds)
            questions.add(dao.findById(id).get());

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses) {
            Question question = dao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }
        return new  ResponseEntity<>(right, HttpStatus.OK);
    }
}
