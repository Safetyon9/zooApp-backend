package com.betacom.utilities;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
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

	
	public static PagamentiDTO buildPagamentoDTO(Pagamenti p){
	    return PagamentiDTO.builder()
	            .id(p.getId())
	            .importo(p.getImporto())
	            .stato(p.getStato().toString())
	            .dataEsecuzione(p.getDataEsecuzione())
	            .ordineId(p.getOrdine().getId())
	            .metodoPagamentoId(p.getId())
	            .couponId(p.getId())
	            .build();
	}
	
	public static List<PagamentiDTO> buildPagamentoDTO(List<Pagamenti> lP){
	    return lP.stream()
				.map(p -> PagamentiDTO.builder()
		            .id(p.getId())
		            .importo(p.getImporto())
		            .stato(p.getStato().toString())
		            .dataEsecuzione(p.getDataEsecuzione())
		            .ordineId(p.getOrdine().getId())
		            .metodoPagamentoId(p.getId())
		            .couponId(p.getId())
		            .build()
				).collect(Collectors.toList());
	}
}
