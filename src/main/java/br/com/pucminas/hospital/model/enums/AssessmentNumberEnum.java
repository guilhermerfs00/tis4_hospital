package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AssessmentNumberEnum {

    PRIMEIRA(1),
    SEGUNDA(2),
    FINAL(3);

    @Getter
    private Integer value;
}
