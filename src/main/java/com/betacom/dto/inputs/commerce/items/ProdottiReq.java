package com.betacom.dto.inputs.commerce.items;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdottiReq {

    private Integer itemId;
    private BigDecimal dimensioni;
    private BigDecimal peso;
    private Integer stock;
    private Long sku;
    private String categoria;

}