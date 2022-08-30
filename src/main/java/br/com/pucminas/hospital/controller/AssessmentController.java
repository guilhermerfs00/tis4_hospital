package br.com.pucminas.hospital.controller;


import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AssessmentController {

    @Autowired
    private AssessmentService service;

    @PostMapping(value = "/create-assessment-patient")
    public ResponseEntity<AssessmentDTO> createAssessment(@RequestBody AssessmentDTO assessmentDTO) {
        var response = service.createAssessment(assessmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}