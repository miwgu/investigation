/*package com.example.investigation.mappers;

import com.example.investigation.models.entity.Patient;
import com.example.investigation.models.dto.PatientDTO;
import com.example.investigation.services.answer.AnswerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class PatientMapper_old2 {
    @Autowired
    protected AnswerService answerService;
    @Mapping(target="answers", source="answers") //, qualifiedByName= "answersToIds"
    public abstract PatientDTO patientToPatientDto (Patient patient);
    @Mapping(target="answers",source="answers") //, qualifiedByName = "answerIdsToAnswers"
    public abstract Patient patientDtoToPatient (PatientDTO patientDTO);

    @Mapping(target = "answers", source="answers", qualifiedByName = "answersToIds")
    public abstract Collection<PatientDTO> patientToPatientDto(Collection<Patient> patients);

//
//    @Named("answersToIds")
//    List<Long> map(List<Answer> source){
//        if(source == null)
//            return null;
//        return source.stream().map(s -> s.getId()).collect(Collectors.toList());
//    }
//    @Named("answerIdsToAnswers")
//    List<Answer> mapAnswerIdsToAnswers(List<Long>ids){
//        if(ids==null)
//            return null;
//        List<Answer>answers = new ArrayList<>();
//        ids.stream().map(id -> answers.add(answerService.findById(id)));
//        return answers;
//    }
}

 */
