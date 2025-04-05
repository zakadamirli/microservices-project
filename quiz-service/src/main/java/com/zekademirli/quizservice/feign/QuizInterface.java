package com.zekademirli.quizservice.feign;

import com.zekademirli.quizservice.model.QuestionWrapper;
import com.zekademirli.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName,
                                                           @RequestParam Integer numQuestions);

    @PostMapping("/question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
