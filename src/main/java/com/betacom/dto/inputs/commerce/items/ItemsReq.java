package com.betacom.dto.inputs.commerce.items;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsReq {
	private String id;
    private String nome;
    private String descrizione;
    private String urlImmagine;
    private BigDecimal prezzo;
}