package com.example.investigation.mappers;

import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.entity.AnswerOption;
import com.example.investigation.models.entity.Patient;
import com.example.investigation.models.dto.AnswerDTO;
import com.example.investigation.services.answer_option.AnswerOptionService;
import com.example.investigation.services.patient.PatientService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel ="spring")
public  abstract class AnswerMapper {
    //@Autowired
    protected PatientService patientService;
    //@Autowired
    protected AnswerOptionService answerOptionService;


//        public AnswerMapper(PatientService patientService, AnswerOptionService answerOptionService) {
//        this.patientService = patientService;
//        this.answerOptionService = answerOptionService;
//    }



    @Mapping(target="patient", source="patient.id")
    @Mapping(target="answerOption", source="answerOption.id")
    public abstract AnswerDTO answerToAnswerDto(Answer answer);


    @Mapping(target="patient", source="patient.id")
    @Mapping(target="answerOption", source="answerOption.id")
    public abstract Collection<AnswerDTO> answerToAnswerDto( Collection<Answer> answers);

    @Mapping(target="patient",source="patient",qualifiedByName = "patientIdToPatient" )
    @Mapping(target="answerOption", source="answerOption",qualifiedByName = "answerOptionIdToAnswerOption")
    public abstract Answer answerDtoToAnswer(AnswerDTO answerDTO);
    @Named("patientIdToPatient")
    Patient mapPatientIdToAnswer (long id ){
        return patientService.findById(id);
    }
    @Named("answerOptionIdToAnswerOption")
    AnswerOption mapAOIdToAnswer(long id){
        return answerOptionService.findById(id);
    }
}