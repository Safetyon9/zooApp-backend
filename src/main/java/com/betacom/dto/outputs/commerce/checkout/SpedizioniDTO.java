package com.betacom.dto.outputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class SpedizioniDTO {
	
	private Integer id;

    private String indirizzo;

    private String corriere;

    private String trackingNumber;

    private BigDecimal costo;

    private String stato;

    private LocalDate dataAggiornamento;

    private Integer ordineId;
}
