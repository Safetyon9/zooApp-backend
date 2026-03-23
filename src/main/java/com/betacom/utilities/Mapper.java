package com.betacom.utilities;

import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.Clienti;
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
	
	
	public static ClientiDTO buildClienteDTO(Clienti c){
	    return ClientiDTO.builder()
	            .id(c.getId())
	            .email(c.getEmail())
	            .nome(c.getNome())
	            .cognome(c.getCognome())
	            .indirizzo(c.getIndirizzo())
	            .utenteId(c.getUtenteId())
	            .build();
	}//devo sistemare utenti
	
	
}
