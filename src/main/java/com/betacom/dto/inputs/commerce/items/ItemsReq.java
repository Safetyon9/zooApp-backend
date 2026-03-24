package com.betacom.dto.inputs.commerce.items;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemsReq {
	
	private Integer id;
	
    private String nome;
    
    private String descrizione;
    
    private String urlImmagine;
    
    private BigDecimal prezzo;
}