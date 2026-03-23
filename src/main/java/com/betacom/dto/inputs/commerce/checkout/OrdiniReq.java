package com.betacom.dto.inputs.commerce.checkout;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdiniReq {

    private Integer id;                 // usato per update
    private Integer clienteId;
    private String indirizzo;           // aggiornabile
    private List<OggettiOrdiniReq> oggetti;

}