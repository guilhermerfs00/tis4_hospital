package br.com.pucminas.hospital.controller;


import br.com.pucminas.hospital.model.dto.PersonDTO;
import br.com.pucminas.hospital.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "limit", defaultValue = "12") int limit) {
        var personDTOList = service.findAll(page, limit);
        return new ResponseEntity<>(personDTOList, HttpStatus.OK);
    }
}