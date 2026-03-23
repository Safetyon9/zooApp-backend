package com.betacom.utilities;

import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
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
	
	private OggettiOrdiniDTO buildOgettiOrdiniDTO (OggettiOrdini oo) {
        return OggettiOrdiniDTO.builder()
                .id(oo.getId())
                .itemId(oo.getItem().getId())
                .nomeItem(oo.getItem().getNome())
                .quantita(oo.getQuantita())
                .prezzoUnitario(oo.getPrezzoUnitario())
                .prezzoTotale(oo.getPrezzoTotale())
                .build();
    }

}
