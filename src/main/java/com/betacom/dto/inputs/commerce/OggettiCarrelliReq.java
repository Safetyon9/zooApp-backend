package com.betacom.dto.inputs.commerce;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OggettiCarrelliReq {
	private Integer id;

	private Integer quantita;
	
	private Integer prezzoTotale;
	
    private Integer carrelloId;
	
    private Integer itemId;
}
