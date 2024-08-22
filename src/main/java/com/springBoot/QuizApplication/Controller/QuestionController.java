package com.springBoot.QuizApplication.Controller;

import com.springBoot.QuizApplication.Model.Question;
import com.springBoot.QuizApplication.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{topic}")
    public List<Question> getQuestionByCategory(@PathVariable("topic") String topic){
        return questionService.getQuestionsByCatgory(topic);
    }

    @PostMapping("add")
    public String AddQuestion(@RequestBody Question question){
        return questionService.AddQuestion(question);

    }

    @DeleteMapping("delete/{id}")
    public String DeleteQuestion(@PathVariable("id") int id){
        return questionService.DeleteQuestion(id);
    }
}
