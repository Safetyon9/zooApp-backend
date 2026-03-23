package com.betacom.dto.outputs.commerce.items;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsDTO {
    private Integer id;
    private String nome;
    private String descrizione;
    private String urlImmagine;
    private BigDecimal prezzo;
}