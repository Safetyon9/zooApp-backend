package com.betacom.dto.outputs.commerce.items;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProdottiDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private BigDecimal prezzo;
    private BigDecimal dimensioni;
    private BigDecimal peso;
    private Integer stock;
    private Long sku;
    private Integer categoriaId;

}