package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Question;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void saveQuestion(Question newQuestion) {
        questionRepository.save(newQuestion);
    }

    @Override
    public void deleteQuestionById(int id) throws UnFoundResultData{
        if(null == questionRepository.findById(id)){
            throw new UnFoundResultData();
        }
        questionRepository.deleteById(id);
    }

    @Override
    public void updateQuestion(int id, Question newQuestion) {
        Question oldQuestion = questionRepository.findById(id);
        if(null == oldQuestion){
            throw new UnFoundResultData();
        }
        oldQuestion.setText(newQuestion.getText());

        questionRepository.save(oldQuestion);
    }

    @Override
    public Question findQuestionById(int id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findAllQuestions() {
            return questionRepository.findAll();
    }
}
