package com.betacom.dto.inputs.commerce;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class GiornateReq {

	private Integer Id;
    private LocalDate data;
    private Integer eventoId;
}