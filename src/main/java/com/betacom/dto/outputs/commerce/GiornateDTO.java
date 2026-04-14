package com.betacom.dto.outputs.commerce;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GiornateDTO {

    private Integer id;
    private LocalDate data;
    private Boolean aperto;
    private Integer eventoId;
    private Integer stock;
}