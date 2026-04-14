package com.betacom.dto.inputs;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniPagReq {
	
	private OrdiniReq ordini;
	private PagamentiReq pagamenti;
	private List<OggettiOrdiniReq> righe;

}
