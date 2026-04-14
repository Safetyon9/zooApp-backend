package com.betacom.dto.outputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OggettiOrdiniDTO {

    private Integer id;
    private Integer itemId;
    private String nomeItem;
    private Integer quantita;
    private BigDecimal prezzoUnitario;
    private BigDecimal prezzoTotale;
    private Integer ordineId;
    private LocalDate dataVisita;

}