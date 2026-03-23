package com.betacom.dto.outputs.commerce.items;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class BigliettiGiornateDTO {
	
	private Integer id;

    private Integer bigliettoId;

    private Integer giornataId;

    private Integer eventoId;

    private BigDecimal prezzo;

    private Integer stock;
}
