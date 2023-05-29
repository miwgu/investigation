package com.example.investigation.services.answer_option;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Answer;
import com.example.investigation.models.AnswerOption;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerOptionServiceImpl implements AnswerOptionService{

    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public AnswerOption findById(long id) {
     return getAnswerOption(id);
    }

    public AnswerOption getAnswerOption(long id) {
        return answerOptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This answer_option_id, " + id + " does not exist."));
    }

    @Override
    public Collection<AnswerOption> findAllByItem(String item) {
        return answerOptionRepository.findAllByItem(item);
    }

    @Override
    public Collection<AnswerOption> findAll() {
        return answerOptionRepository.findAll();
    }

    @Override
    public Collection<AnswerOption> findByQuestionId(long question_id) {
        return answerOptionRepository.findByQuestionId(question_id);
    }

    @Override
    public AnswerOption add(AnswerOption answerOption) {
        if (answerOption != null)
            return answerOptionRepository.save(answerOption);
        return null;
    }
    @Override
    public AnswerOption update(long id, AnswerOption answerOption) {

        AnswerOption existingAnswerOption = getAnswerOption(id);

        if(answerOption.getItem()!=null && !answerOption.getItem().isEmpty()){
            existingAnswerOption.setItem(answerOption.getItem());
        }
        return answerOptionRepository.save(existingAnswerOption);
    }

    @Override
    public AnswerOption updateQuestionById(long question_id, long ao_id) {
        AnswerOption existingAnswerOption = getAnswerOption(ao_id);
        if(existingAnswerOption.getQuestion().getId()!=0){
            existingAnswerOption.setQuestion(questionRepository.getById(question_id));
            answerOptionRepository.save(existingAnswerOption);
        }
        return answerOptionRepository.getById(ao_id);
    }

    @Override
    public void deleteById(Long id) {
        AnswerOption findAnswerOptionById = answerOptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("THIS_ANSWER_OPTION_ID, " + id + " DOES_NOT_EXIST."));

        if (findAnswerOptionById != null) {
            boolean canDelete = true;


            List<Answer> answers = answerRepository.findByAnswerOptionId(id);
            if (!answers.isEmpty()) {
                canDelete = false;
                System.out.println("You can not delete this answer_option because user already answered");
                new ResourceNotFoundException("YOU_CANNOT_DELETE_BECAUSE_USER_ALREADY_ANSWERED");
            }

            if (canDelete) {
                AnswerOption answerOption = findById(id);
                answerOptionRepository.delete(answerOption);
                System.out.println("AnswerOption id, " + id + " is deleted");
            }

        }
    }
}
