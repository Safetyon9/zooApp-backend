package com.betacom.dto.outputs.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class PagamentiDTO {
	
	private Integer id;

    private BigDecimal importo;

    private String stato;
    
    private LocalDateTime dataCreazione;

    private LocalDateTime dataEsecuzione;

    private Integer ordineId;
    
    private String idRicevuta;
    
	private String urlRicevutaPDF;

    private Integer metodoPagamentoId;
    
    private String metodoPagamentoNome;

    private Integer couponId;
}
