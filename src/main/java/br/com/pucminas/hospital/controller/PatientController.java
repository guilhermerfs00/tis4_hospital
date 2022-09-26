//package br.com.pucminas.hospital.controller;
//
//
//import br.com.pucminas.hospital.model.dto.PatientDTO;
//import br.com.pucminas.hospital.services.PatientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class PatientController {
//
//    @Autowired
//    private PatientService service;
//
//    @GetMapping(value = "/find-all")
//    public ResponseEntity<List<PatientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
//        var response = service.findAllPatient(page, limit);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
////    @GetMapping(value = "/patient-filter")
////    public ResponseEntity<PatientDTO> patientByFilter(@RequestParam MultiValueMap<String, String> requestParams) {
////        var response = service.patientFilter(requestParams);
////        return null;
////    }
//
//    @PostMapping(value = "/create-patient")
//    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
//        var response = service.createPatient(patientDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//}