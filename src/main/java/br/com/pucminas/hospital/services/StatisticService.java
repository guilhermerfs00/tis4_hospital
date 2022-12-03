package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.model.dto.ParamsStatisticDTO;
import br.com.pucminas.hospital.model.dto.StatisticDTO;
import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.pucminas.hospital.model.enums.AssesmentCancelReasonEnum.*;
import static br.com.pucminas.hospital.model.enums.AssesmentStatusEnum.*;

@Service
public class StatisticService {

    @Autowired
    private AssessmentRepository repository;


    public StatisticDTO getStatisticByAssessment(ParamsStatisticDTO paramsStatisticDTO) {

        StatisticDTO statisticDTO = new StatisticDTO();

        var assessmentList = repository.getStatisticByAssessment(paramsStatisticDTO.getInitialDate(), paramsStatisticDTO.getFinalDate());

        assessmentList.stream().forEach(a -> {

            statisticDTO.setTotal(statisticDTO.getMissedCalls() + 1);

            if (a.getStatus().equals(RESPONDIDO)) {
                statisticDTO.setAnsweredCalls(statisticDTO.getAnsweredCalls() + 1);
            }

            if (a.getStatus().equals(ENVIADO)) {
                statisticDTO.setMissedCalls(statisticDTO.getMissedCalls() + 1);
            }

            if (a.getCancelReason().equals(TELEFONE_INEXISTE)) {
                statisticDTO.setNonExistentNumber(statisticDTO.getNonExistentNumber() + 1);
            }

            if (a.getCancelReason().equals(TELEFONE_INDISPONIVEL)) {
                statisticDTO.setUnavailableNumber(statisticDTO.getUnavailableNumber() + 1);
            }

            if (a.getCancelReason().equals(TELEFONE_NAO_PERTENCE)) {
                statisticDTO.setIsntPatientsPhone(statisticDTO.getIsntPatientsPhone() + 1);
            }

            if (a.getIsPatientDead().equals(Boolean.TRUE)) {
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