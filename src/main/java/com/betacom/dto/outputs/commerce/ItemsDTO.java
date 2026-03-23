package com.betacom.dto.outputs.commerce;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class ItemsDTO {
	private Integer id;

    private String nome;
	
    private String descrizione;
	
    private String urlImmagine;

    private BigDecimal prezzo;
}
