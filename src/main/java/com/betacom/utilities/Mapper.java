package com.betacom.utilities;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;

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
	            .utenteId(c.getUtente() != null ? c.getUtente().getId() : null)
	            .build();
	}
	
	public static EventiDTO buildEventoDTO(Eventi e){
	    return EventiDTO.builder()
	            .id(e.getId())
	            .tipoEvento(e.getTipoEvento())
	            .dataInizio(e.getDataInizio())
	            .dataFine(e.getDataFine())
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
	            .ordineId(p.getOrdine() != null ? p.getOrdine().getId() : null)
	            .metodoPagamentoId(p.getMetodoPagamento() != null ? p.getMetodoPagamento().getId() : null)
	            .couponId(p.getCoupon() != null ? p.getCoupon().getId() : null)
	            .build();
	}
	
	public static SpedizioniDTO buildSpedizioniDTO(Spedizioni s){
	    return SpedizioniDTO.builder()
	    		.id(s.getId())
                .indirizzo(s.getIndirizzo())
                .corriere(s.getCorriere())
                .trackingNumber(s.getTrackingNumber())
                .costo(s.getCosto())
                .stato(s.getStato().toString())
                .dataAggiornamento(s.getDataAggiornamento())
                .ordineId(s.getOrdine() != null ? s.getOrdine().getId() : null)
                .build();
	}
	
	public static BigliettiGiornateDTO buildBigliettiGiornateDTO(BigliettiGiornata bg) {
	    return BigliettiGiornateDTO.builder()
	            .id(bg.getId())
	            .bigliettoId(bg.getBiglietto() != null ? bg.getBiglietto().getId() : null)
	            .giornataId(bg.getGiornata() != null ? bg.getGiornata().getId() : null)
	            .eventoId(bg.getEvento() != null ? bg.getEvento().getId() : null)
	            .prezzo(bg.getPrezzo())
	            .stock(bg.getStock())
	            .build();
	}
	
	public static GiornateDTO buildGiornataDTO(Giornate g){
	    return GiornateDTO.builder()
	            .id(g.getId())
	            .data(g.getData())
	            .eventoId(g.getEvento() != null ? g.getEvento().getId() : null)
	            .build();
	}
	
}
