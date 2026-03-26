package com.betacom.dto.outputs.commerce;
import java.util.List;

import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class ClientiDTO  {
    private Integer id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String utenteUsername;
    private Integer carrelloId;
    private List<OrdiniDTO> ordini;
}
