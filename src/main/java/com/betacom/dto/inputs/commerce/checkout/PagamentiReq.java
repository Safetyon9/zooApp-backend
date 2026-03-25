package com.betacom.dto.inputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private Integer metodoPagamentoId;

    private Integer couponId;
}
