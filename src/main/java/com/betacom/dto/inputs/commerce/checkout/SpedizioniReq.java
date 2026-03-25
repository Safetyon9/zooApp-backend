package com.betacom.dto.inputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpedizioniReq {

	private Integer id;

    private Integer corriereId;

    private String trackingNumber;

    private BigDecimal costo;

    private String stato;

    private LocalDate dataAggiornamento;

    private Integer ordineId;
}
