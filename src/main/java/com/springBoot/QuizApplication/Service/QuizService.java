package com.springBoot.QuizApplication.Service;

import com.springBoot.QuizApplication.DAO.QuestionDao;
import com.springBoot.QuizApplication.DAO.QuizDao;
import com.springBoot.QuizApplication.Model.Question;
import com.springBoot.QuizApplication.Model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int count, String title){

        List<Question> questions=questionDao.findRandomQuestionsByCategory(category,count);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
