package com.betacom.dto.inputs.commerce.checkout;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponsReq {
    
    private Integer id;          
    private String codice;
    private String tipo;
    private BigDecimal valore;
    private Boolean attivo;
    private String dataInizio;
    private String dataFine;
}
