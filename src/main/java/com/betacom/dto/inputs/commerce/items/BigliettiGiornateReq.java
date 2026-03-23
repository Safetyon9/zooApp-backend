package com.betacom.dto.inputs.commerce.items;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BigliettiGiornateReq {

	private Integer id;

    private Integer bigliettoId;

    private Integer giornataId;

    private Integer eventoId;

    private BigDecimal prezzo;

    private Integer stock;
}
