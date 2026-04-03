package com.betacom.dto.outputs.commerce;

import java.util.List;

import com.betacom.persistence.entity.commerce.OggettiCarrelli;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CarrelliDTO {
	private Integer id;
	private Integer clienteId;                        
	private List<OggettiCarrelliDTO> oggettiCarrello;
}
