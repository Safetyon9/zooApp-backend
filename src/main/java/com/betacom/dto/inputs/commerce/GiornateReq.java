package com.betacom.dto.inputs.commerce;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
public class GiornateReq {
	
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    private Boolean aperto;
    private Integer eventoId;
    private Integer stock;
    
}