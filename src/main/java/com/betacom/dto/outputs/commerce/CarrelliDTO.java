package com.betacom.dto.outputs.commerce;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CarrelliDTO {
	private Integer id;

	private Integer clienteId;                        

	private ClientiDTO cliente;                        

	private List<OggettiCarrelliDTO> oggettiCarrello;
}
