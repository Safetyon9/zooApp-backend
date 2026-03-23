package com.betacom.dto.outputs.commerce.items;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder

public class BigliettiDTO extends ItemsDTO {

    private String tipo;

}
