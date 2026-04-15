package com.betacom.dto.outputs.commerce;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class OggettiCarrelliDTO {
	private Integer id;
	
	private BigDecimal prezzoUnitario;
	
	private BigDecimal prezzoTotale;
	
    private Integer carrelloId;
    
    private Integer itemId;
    private String tipo;

    private String nome;
    private String urlImmagine;

    private Integer quantita;
}
