package com.example.investigation.services.answer;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Answer;
import com.example.investigation.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    @Override
    public Answer findById(long id) {
        return getAnswer(id);
    }

    private Answer getAnswer(long id) {
        return answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This id, " + id + " does not exist."));
    }

    @Override
    public Collection<Answer> findAllByPatientId(long patient_id) {
        return answerRepository.findByPatientId(patient_id);
    }

    @Override
    public Collection<Answer> findAllByAnswerOptionId(long ao_id) {
        return answerRepository.findByAnswerOptionId(ao_id);
    }

    @Override
    public Collection<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer add(Answer answer) {
        if(answer != null)
          return answerRepository.save(answer);
         return null;
    }
}
