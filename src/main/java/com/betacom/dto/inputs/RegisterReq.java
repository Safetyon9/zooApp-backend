package com.betacom.dto.inputs;

import com.betacom.dto.inputs.commerce.ClientiReq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterReq {
	private UtentiReq utente;
    private ClientiReq cliente;
}
