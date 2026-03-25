package com.betacom.dto.inputs.commerce.items;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BigliettiReq {
	private Integer itemId;
	private String nome;
    private String descrizione;
    private String urlImmagine;
    private BigDecimal prezzo;

    private Integer tipoId;
    //test
}
