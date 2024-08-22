package com.springBoot.QuizApplication.Service;

import com.springBoot.QuizApplication.DAO.QuestionDao;
import com.springBoot.QuizApplication.DAO.QuizDao;
import com.springBoot.QuizApplication.Model.Question;
import com.springBoot.QuizApplication.Model.QuestionWrapper;
import com.springBoot.QuizApplication.Model.Quiz;
import com.springBoot.QuizApplication.Model.quizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFromDb=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(Question q:questionsFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<quizResponse> quizResponses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> quizQuestions=quiz.getQuestions();
        int i=0,right=0;
        for(quizResponse response:quizResponses){
            if(response.getQuizResponse().equals(quizQuestions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
