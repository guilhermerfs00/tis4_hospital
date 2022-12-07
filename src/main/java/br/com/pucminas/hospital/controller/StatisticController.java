package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.StatisticDTO;
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

    @GetMapping(value = "/get-statistic-by-assessment/{initialDate}/{finalDate}")
    public ResponseEntity<StatisticDTO> getStatisticByAssessment(@PathVariable(value="initialDate") String initialDate, @PathVariable(value="initialDate") String finalDate) {
        var response = service.getStatisticByAssessment(initialDate, finalDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}