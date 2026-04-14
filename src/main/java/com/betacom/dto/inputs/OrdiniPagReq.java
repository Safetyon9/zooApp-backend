package com.betacom.dto.inputs;

import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class OrdiniPagReq {
	
	private OrdiniReq ordini;
	private PagamentiReq pagamenti;

}
