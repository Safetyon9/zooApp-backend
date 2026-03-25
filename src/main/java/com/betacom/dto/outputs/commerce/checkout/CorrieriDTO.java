package com.betacom.dto.outputs.commerce.checkout;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class CorrieriDTO {
	private Integer id;

    private String nome;
}
