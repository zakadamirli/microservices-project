package com.zekademirli.quizservice.service;

import com.zekademirli.quizservice.dao.QuizDao;
import com.zekademirli.quizservice.feign.QuizInterface;
import com.zekademirli.quizservice.model.QuestionWrapper;
import com.zekademirli.quizservice.model.Quiz;
import com.zekademirli.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizDao.save(quiz);
        return null;
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionIds);
        return questions;
    }


    public ResponseEntity<Integer> calculateResult(Integer id,List<Response> responses) {
        ResponseEntity<Integer> result = quizInterface.getScore(responses);
        return result;
    }
}