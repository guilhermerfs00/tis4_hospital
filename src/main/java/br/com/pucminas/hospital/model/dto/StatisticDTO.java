package br.com.pucminas.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private Integer answeredCalls = 0;
    private Integer missedCalls = 0;
    private Integer deaths = 0;
    private Integer total = 0;
    private Integer unsuccessfully = 0;
    private Integer didtRespondThreeAttempts = 0;
    private Integer nonExistentNumber = 0;
    private Integer unavailableNumber = 0;
    private Integer isntPatientsPhone = 0;

}