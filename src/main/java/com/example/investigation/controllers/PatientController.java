package com.example.investigation.controllers;

import com.example.investigation.mappers.PatientMapper;
import com.example.investigation.models.Patient;
import com.example.investigation.models.dto.PatientDTO;
import com.example.investigation.services.patient.PatientService;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final PatientMapper patientMapper;
    private final PatientService patientService;

//    public PatientController(PatientService patientService, PatientMapper patientMapper) {
//        this.patientService = patientService;
//        this.patientMapper = patientMapper;
//
//    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        Collection<PatientDTO> patients = patientMapper.patientToPatientDto(
                patientService.findAll());
        return ResponseEntity.ok(patients);
    }

    /*
    * http://localhost:8080/api/v1/patient/search/byId/1
    * */
    @GetMapping("search/byId/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable long id) {
        PatientDTO patientDTO = patientMapper.patientToPatientDto(patientService.findById(id));
        return ResponseEntity.ok(patientDTO);

    }

    /*
    * http://localhost:8080/api/v1/patient/search/byName/aki
    * You can use @PathVariable if you want to write "search/name/{name}"
    * */
    @GetMapping("search/byName/{name}")
    public ResponseEntity<Collection<PatientDTO>> getAllByPatientName(@PathVariable String name) {
        Collection<PatientDTO> patientDTOS = patientMapper.patientToPatientDto(patientService.findByName(name));
        return ResponseEntity.ok(patientDTOS);
    }

    /*
    * http://localhost:8080/patient/search/bySocialnumber?sn=195001011234
    * You cannot use @PathVariable when you write ?sn=195001011234 You need to use @RequestParam
    * */
    @GetMapping("search/bySocialnumber")
    public ResponseEntity<Collection<PatientDTO>> getBySocialNumber (@RequestParam String sn) {
        Collection<PatientDTO> patientDTOS = patientMapper.patientToPatientDto(patientService.findBySocialNumber(sn));
        return ResponseEntity.ok(patientDTOS);
    }

    /*
     * http://localhost:8080/api/v1/patient/search/byEmail/sample2@hotmail.com
     * You can use @PathVariable if you want to write "search/name/{name}"
     * */

    @GetMapping("search/byEmail/{email}")
    public ResponseEntity<Collection<PatientDTO>> getByEmail (@PathVariable  String email) {
        Collection<PatientDTO> patientDTOS = patientMapper.patientToPatientDto(patientService.findByEmail(email));
        return ResponseEntity.ok(patientDTOS);
    }


    /*
    * http://localhost:8080/api/v1/patient/add
    * */

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO){
        Patient patient = patientService.add(patientMapper.patientDtoToPatient(patientDTO));
        // return ResponseEntity.status(HttpStatus.CREATED).build();
        return patientMapper.patientToPatientDto(patient);

    }

   /*
   * http://localhost:8080/api/v1/patient/update/12
   * */
/*

    @PutMapping("/update/{id}")
    public ResponseEntity updatePatient (@RequestBody PatientDTO patientDTO, @PathVariable long id){
       if(id != patientDTO.getId())
           return ResponseEntity.notFound().build();
           patientService.update(patientMapper.patientDtoToPatient(patientDTO));

       return ResponseEntity.noContent().build();
    }
    */



/*
    @PutMapping("/update/{id}")
    public PatientDTO updatePatient (@RequestBody PatientDTO patientDTO, @PathVariable long id) {
//       if (id != patientDTO.getId()) {
//            throw new RuntimeException("The patient with id " + id + "does not exist.");
//        }
        System.out.println("id: "+id);
        System.out.println("patientDTO.getId()" +patientDTO.getId());

        Patient patient = patientService.update(patientMapper.patientDtoToPatient(patientDTO));

        return patientMapper.patientToPatientDto(patient);
    }

 */
    @PatchMapping ("/update/{id}")
    public PatientDTO updatePatient (@RequestBody PatientDTO patientDTO, @PathVariable long id){
        Patient patient = patientMapper.patientDtoToPatient(patientDTO);
        return patientMapper.patientToPatientDto(patientService.update(id, patient));
    }



   /*
   * http://localhost:8080/api/v1/patient/delete/13
   * */

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)//200
    public String deletePatient(@PathVariable long id){
        return patientService.deleteById(id);
    }



}

