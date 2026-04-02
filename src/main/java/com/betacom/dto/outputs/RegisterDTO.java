package com.betacom.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RegisterDTO {
	
	private String userName;
    private String email;
    private String role;
    

    private Integer id;
    private String nome;
    private String cognome;
    private String indirizzo;
	private String provincia;
	private String comune;
	private String cap;
	private String telefono;



    
    private Integer carrelloId;
}
