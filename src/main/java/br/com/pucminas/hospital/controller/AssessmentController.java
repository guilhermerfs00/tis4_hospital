package br.com.pucminas.hospital.controller;


import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.model.dto.AssessmentDetailsDTO;
import br.com.pucminas.hospital.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssessmentController {

    @Autowired
    private AssessmentService service;

    @GetMapping(value = "/find-assessment-by-register/{patientRegister}")
    public ResponseEntity<List<AssessmentDTO>> findAssessmentByRegister(@PathVariable String patientRegister) {
        var response = service.findAssessmentByRegister(patientRegister);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/update-assessment/{idPatient}")
    public ResponseEntity<AssessmentDTO> updateAssessmente(@PathVariable Long idPatient, @RequestBody AssessmentDetailsDTO assessmentDetailsDTO) {
        var response = service.updateAssessment(idPatient, assessmentDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}