package com.betacom.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UtentiResp {
	
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
	private Integer carrelloId;
	private Boolean isValidate;
	private String validationToken;
	private Integer clienteId;
}
