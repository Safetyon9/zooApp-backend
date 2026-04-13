package com.betacom.dto.outputs.commerce;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class EventiDTO {

    private Integer id;
    private String tipoEvento;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String descrizione;
}