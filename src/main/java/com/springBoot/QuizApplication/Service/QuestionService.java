package com.springBoot.QuizApplication.Service;

import com.springBoot.QuizApplication.Controller.QuestionController;
import com.springBoot.QuizApplication.DAO.QuestionDao;
import com.springBoot.QuizApplication.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCatgory(String topic){
        try{
            return new ResponseEntity<>(questionDao.findByCategory(topic), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<String>  AddQuestion(Question question){
        questionDao.save(question);
        try{
            return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> DeleteQuestion(int id){
        questionDao.deleteById(id);
        try{
            return new ResponseEntity<>("Question Deleted", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
    }
}
