package com.betacom.utilities;

import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.items.Biglietti;

public class Mapper {


	public static BigliettiDTO buildBigliettiDTO(Biglietti b){
	    return BigliettiDTO.builder()
	            .id(b.getId())
	            .nome(b.getNome())
	            .descrizione(b.getDescrizione())
	            .urlImmagine(b.getUrlImmagine())
	            .prezzo(b.getPrezzo())
	            .tipo(b.getTipo())
	            .build();
	}
}
