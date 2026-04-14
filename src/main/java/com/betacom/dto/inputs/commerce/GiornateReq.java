package com.betacom.dto.inputs.commerce;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class GiornateReq {
	
    private Integer id;
    private LocalDate data;
    private Boolean aperto;
    private Integer eventoId;
    private Integer stock;
    
}