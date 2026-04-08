package com.betacom.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UtentiReqResp {
	
	private String userName;
    private String email;
    private String role; 
    private String nome;
    private String cognome;
    private String indirizzo;
    private String comune;
    private String cap;
	private String telefono; 
	private String provincia;
}
