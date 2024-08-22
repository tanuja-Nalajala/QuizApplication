package com.springBoot.QuizApplication.Controller;

import com.springBoot.QuizApplication.Model.Question;
import com.springBoot.QuizApplication.Model.QuestionWrapper;
import com.springBoot.QuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int count, @RequestParam String title ){
        return quizService.createQuiz(category,count,title);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
}
