package com.betacom.dto.outputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.betacom.enums.StatoOrdine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrdiniDTO {

    private Integer id;
    private Integer clienteId;
    private String nome;
    private String cognome;
    private String indirizzo;
    private LocalDateTime dataOrdine;
    
    private BigDecimal importoTotale;
    
    private StatoOrdine stato;
    private List<OggettiOrdiniDTO> righe;

}