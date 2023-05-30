package com.example.investigation.services.answer;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.entity.AnswerOption;
import com.example.investigation.models.entity.Patient;
import com.example.investigation.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;
    private final PatientRepository patientRepository;
    private final QuestionRepository questionRepository;

    private final AnswerOptionRepository answerOptionRepository;


    @Override
    public Answer findById(long id) {
        return getAnswer(id);
    }

    private Answer getAnswer(long id) {
        return answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This answer_id, " + id + " does not exist."));
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
    public List<Map<String, Object>> findBySurveyId(long survey_id) {
        return answerRepository.findBySurveyId(survey_id);
    }

    @Override
    public Collection<Answer> findAll() {
        return answerRepository.findAll();
    }

    /*
    @Override
    public Answer add(Answer answer) {
        if(answer != null)
           return answerRepository.save(answer);
        return null;
    }

     */
/*
    @Override
    public Answer add(Answer answer,Long patient_id, Long ao_id) {
        answerRepository.save(answer);
        Patient patient= patientRepository.findById(patient_id).orElseThrow(()-> new ResourceNotFoundException("PATIENT_ID_NOT_EXIST"));
        AnswerOption ao = answerOptionRepository.findById(ao_id).orElseThrow(()-> new ResourceNotFoundException("ANSWER_OPTION_ID_NOT_EXIST"));

        answer.setPatient(patient);
        answer.setAnswerOption(ao);

        return answer;
    }

 */


    @Override
    public Answer add(Long patient_id, Long ao_id) {
        Answer answer = new Answer();
        Patient patient= patientRepository.findById(patient_id).orElseThrow(()-> new ResourceNotFoundException("PATIENT_ID_NOT_EXIST"));
        AnswerOption ao = answerOptionRepository.findById(ao_id).orElseThrow(()-> new ResourceNotFoundException("ANSWER_OPTION_ID_NOT_EXIST"));

        answer.setPatient(patient);
        answer.setAnswerOption(ao);

        return answerRepository.save(answer);
    }

    @Override
    public Answer create(Answer toAnswer) {
        Patient patient= patientRepository.findById(toAnswer.getPatient().getId()).orElseThrow(()-> new ResourceNotFoundException("PATIENT_ID_NOT_EXIST"));
        AnswerOption ao = answerOptionRepository.findById(toAnswer.getAnswerOption().getId()).orElseThrow(()-> new ResourceNotFoundException("ANSWER_OPTION_ID_NOT_EXIST"));

        toAnswer.setPatient(patient);
        toAnswer.setAnswerOption(ao);
        return answerRepository.save(toAnswer) ;
    }
//    @Override
//    public List<Map<String, Object>> getBySurveyId(long surveyId) {
//        Survey survey = surveyRepository.getById(surveyId);
//
//        return answerRepository.findBySurveyId(surveyId);
//    }

}
