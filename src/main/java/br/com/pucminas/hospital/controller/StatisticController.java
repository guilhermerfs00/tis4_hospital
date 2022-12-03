package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.ParamsStatisticDTO;
import br.com.pucminas.hospital.model.dto.StatisticDTO;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import br.com.pucminas.hospital.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    StatisticService service;

    @Autowired
    AssessmentRepository assessmentRepository;

    @GetMapping(value = "/get-statistic-by-assessment")
    public ResponseEntity<StatisticDTO> getStatisticByAssessment(@RequestBody ParamsStatisticDTO paramsStatisticDTO) {
        var response = service.getStatisticByAssessment(paramsStatisticDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}