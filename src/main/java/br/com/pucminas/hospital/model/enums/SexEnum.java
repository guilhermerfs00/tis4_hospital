package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexEnum {

    M("MASCULINO"),
    F("FEMININO");

    private String value;
}
