package com.betacom.dto.inputs;

import com.betacom.dto.inputs.commerce.ClientiReq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class RegisterReq {
	private UtentiReq utente;
    private ClientiReq cliente;
}
