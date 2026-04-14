package com.betacom.dto.inputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OggettiOrdiniReq {

    private Integer id;
    private Integer quantita;
    private Integer ordineId;
    private Integer itemId;
    private BigDecimal prezzoUnitario;
    private BigDecimal prezzoTotale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataVisita;
}