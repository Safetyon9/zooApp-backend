package com.betacom.dto.inputs.commerce.checkout;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagamentiReq {
	
	private Integer id;

    private BigDecimal importo;

    private String stato;

    private Integer ordineId;
    
    private String idRicevuta;
    
	private String urlRicevutaPDF;

    private Integer metodoPagamentoId;

    private Integer couponId;
}
