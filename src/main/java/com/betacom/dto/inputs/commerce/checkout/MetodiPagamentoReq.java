package com.betacom.dto.inputs.commerce.checkout;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MetodiPagamentoReq {
	
	private Integer id;
    private String nome;
    private String provider;
}
