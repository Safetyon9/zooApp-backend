package com.betacom.utilities;

<<<<<<< HEAD
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.Clienti;
=======
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
>>>>>>> 1f8dce9d210e33446c49b1e93153bc5ecebc218e
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
	
<<<<<<< HEAD
	
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
	
	
=======
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

>>>>>>> 1f8dce9d210e33446c49b1e93153bc5ecebc218e
}
