package com.betacom.dto.outputs.commerce.items;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder

public class BigliettiDTO{

	private Integer id;
    private String nome;
    private String descrizione;
    private String urlImmagine;
    private BigDecimal prezzo;
	private String tipoNome;
}
