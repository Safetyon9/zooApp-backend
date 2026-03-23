package com.betacom.dto.outputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CouponsDTO {
	
	private Integer id;          
    private String codice;
    private String tipo;
    private BigDecimal valore;
    private Boolean attivo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
}
