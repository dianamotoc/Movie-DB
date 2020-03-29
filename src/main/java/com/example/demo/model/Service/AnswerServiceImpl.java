package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Answer;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public void saveAnswer(Answer newAnswer) {
        answerRepository.save(newAnswer);
    }

    @Override
    public void deleteAnswerById(int id) throws UnFoundResultData {
        if(null == answerRepository.findById(id)){
            throw new UnFoundResultData();
        }
        answerRepository.deleteById(id);
    }

    @Override
    public void updateAnswer(int id, Answer newAnswer) {
        Answer oldAnswer = answerRepository.findById(id);
        if(null == oldAnswer){
            throw new UnFoundResultData();
        }
        oldAnswer.setText(newAnswer.getText());
        oldAnswer.setId_question(newAnswer.getId_question());
        oldAnswer.setId_user(newAnswer.getId_user());

        answerRepository.save(oldAnswer);
    }
}