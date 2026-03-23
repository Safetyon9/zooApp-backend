package com.betacom.dto.outputs.commerce.checkout;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MetodiPagamentoDTO {
	
	private Integer id;
    private String nome;
    private String provider;
}
