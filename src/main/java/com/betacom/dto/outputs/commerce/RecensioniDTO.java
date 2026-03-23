package com.betacom.dto.outputs.commerce;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class RecensioniDTO {
	
	private Integer id;

    private Integer voto;

    private String testo;

    private String titolo;

    private Boolean generaleZoo;

    private Integer clienteId;

    private Integer itemId;
}
