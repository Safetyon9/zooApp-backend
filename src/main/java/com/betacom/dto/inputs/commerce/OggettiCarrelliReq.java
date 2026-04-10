package com.betacom.dto.inputs.commerce;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OggettiCarrelliReq {

	
	private Integer id;
	private Integer quantita;
    private Integer carrelloId;
    private Integer itemId;

	private BigDecimal prezzoUnitario;
	private BigDecimal prezzoTotale;

}
