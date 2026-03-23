package com.betacom.dto.outputs.commerce;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OggettiCarrelliDTO {
	private Integer id;

	private Integer quantita;
	
	private Integer prezzoTotale;
	
    private Integer carrelloId;
	
    private Integer itemId;
}
