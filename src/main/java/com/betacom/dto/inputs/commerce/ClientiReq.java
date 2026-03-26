package com.betacom.dto.inputs.commerce;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientiReq {
	
	private Integer id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String utenteUsername;
}