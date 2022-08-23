package br.com.pucminas.hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinesException extends RuntimeException {
    public BusinesException(String ex) {
        super(ex);
    }

}
