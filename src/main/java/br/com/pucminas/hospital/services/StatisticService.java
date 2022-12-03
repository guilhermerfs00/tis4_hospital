package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.model.dto.ParamsStatisticDTO;
import br.com.pucminas.hospital.model.dto.StatisticDTO;
import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.pucminas.hospital.model.enums.AssesmentCancelReasonEnum.*;
import static br.com.pucminas.hospital.model.enums.AssesmentStatusEnum.*;

@Service
public class StatisticService {

    @Autowired
    private AssessmentRepository repository;


    public StatisticDTO getStatisticByAssessment(String di, String df) {

        var initialDate = LocalDate.parse(di);
        var finalDate = LocalDate.parse(df);

        StatisticDTO statisticDTO = new StatisticDTO();

        var assessmentList = repository.getStatisticByAssessment(initialDate, finalDate);

        assessmentList.stream().forEach(a -> {

            statisticDTO.setTotal(statisticDTO.getTotal() + 1);

            if (a.getStatus().equals(RESPONDIDO)) {
                statisticDTO.setAnsweredCalls(statisticDTO.getAnsweredCalls() + 1);
            }

            if (a.getStatus().equals(ENVIADO)) {
                statisticDTO.setMissedCalls(statisticDTO.getMissedCalls() + 1);
            }

            if (TELEFONE_INEXISTE.equals(a.getCancelReason())) {
                statisticDTO.setNonExistentNumber(statisticDTO.getNonExistentNumber() + 1);
            }

            if (TELEFONE_INDISPONIVEL.equals(a.getCancelReason())) {
                statisticDTO.setUnavailableNumber(statisticDTO.getUnavailableNumber() + 1);
            }

            if (TELEFONE_NAO_PERTENCE.equals(a.getCancelReason())) {
                statisticDTO.setIsntPatientsPhone(statisticDTO.getIsntPatientsPhone() + 1);
            }

            if (Boolean.TRUE.equals(a.getIsPatientDead())) {
                statisticDTO.setDeaths(statisticDTO.getDeaths() + 1);
            }
        });

        var idsPatient = assessmentList.stream()
                .filter(assessment -> assessment.getAssessmentNumberEnum().equals(AssessmentNumberEnum.TERCEIRA))
                .map(assessment -> assessment.getPatient().getIdPatient())
                .collect(Collectors.toList());


        var assessmentById = repository.getAssessmentsByIdPatient(idsPatient)
                .stream().filter(assessment -> assessment.getStatus().equals(ENVIADO)).collect(Collectors.toList());

        Map<Long, Integer> map = new HashMap<>();

        assessmentById.forEach(assessment
                -> map.put(assessment.getPatient().getIdPatient(), map.getOrDefault(assessment.getPatient().getIdPatient(), 0) + 1)
        );

        statisticDTO.setDidtRespondThreeAttempts((int) map.values().stream().filter(i -> i == 3).count());

        return statisticDTO;
    }
}