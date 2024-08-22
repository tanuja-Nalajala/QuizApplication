package com.springBoot.QuizApplication.Service;

import com.springBoot.QuizApplication.Controller.QuestionController;
import com.springBoot.QuizApplication.DAO.QuestionDao;
import com.springBoot.QuizApplication.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCatgory(String topic){
            return questionDao.findByCategory(topic);
        }
    public String  AddQuestion(Question question){
        questionDao.save(question);
        return "Question Added";
    }

    public String DeleteQuestion(int id){
        questionDao.deleteById(id);
        return "Question Deleted";
    }
}
