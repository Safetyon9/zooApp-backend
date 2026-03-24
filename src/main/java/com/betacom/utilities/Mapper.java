package com.betacom.utilities;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.dto.outputs.commerce.RecensioniDTO;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;
import com.betacom.dto.outputs.commerce.checkout.MetodiPagamentoDTO;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.dto.outputs.commerce.items.ProdottiDTO;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.OggettiCarrelli;
import com.betacom.persistence.entity.commerce.Recensioni;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;
import com.betacom.persistence.entity.commerce.items.Prodotti;

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

	

	public static OggettiOrdiniDTO buildOgettiOrdiniDTO (OggettiOrdini oo) {
        return OggettiOrdiniDTO.builder()
                .id(oo.getId()) 
                .itemId(oo.getItem().getId())
                .nomeItem(oo.getItem().getNome())
                .quantita(oo.getQuantita())
                .prezzoUnitario(oo.getPrezzoUnitario())
                .prezzoTotale(oo.getPrezzoTotale())
                .build();
    }
	
	public static OrdiniDTO buildOrdiniDTO(Ordini o) {
		return OrdiniDTO.builder()
				.id(o.getId())
				.clienteId(o.getCliente() != null ? o.getCliente().getId() : null)
				.nome(o.getNome())
				.cognome(o.getCognome())
				.indirizzo(o.getIndirizzo())
				.dataOrdine(o.getDataOrdine())
				.build();
		
	}
	
	public static RecensioniDTO buildRecensioniDTO (Recensioni r) {
		return RecensioniDTO.builder()
				.id(r.getId())
				.voto(r.getVoto())
				.testo(r.getTesto())
				.titolo(r.getTitolo())
				.generaleZoo(r.getGeneraleZoo())
				.clienteId(r.getCliente() != null ? r.getCliente().getId() : null)
				.itemId(r.getItem() != null ? r.getItem().getId() : null)
				.build();
	}
	
	public static ProdottiDTO buildProdottiDTO(Prodotti p) {
		return ProdottiDTO.builder()
                .id(p.getId())
                .itemId(p.getId())
                .nome(p.getNome())
                .descrizione(p.getDescrizione())
                .prezzo(p.getPrezzo())
                .dimensioni(p.getDimensioni())
                .peso(p.getPeso())
                .stock(p.getStock())
                .sku(p.getSku())
                .categoria(p.getCategoria())
                .build();
		
	}
	
	public static UtentiDTO buildUtentiDTO(Utenti u) {
		return UtentiDTO.builder()
			    .id(u.getId())
			    .userName(u.getUserName())
			    .email(u.getEmail())
			    .role(u.getRole().name())
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
	
	public static OggettiCarrelliDTO buildOggettiCarrelliDTO(OggettiCarrelli oc) {
		
		return OggettiCarrelliDTO.builder()
				.id(oc.getId())
				.prezzoTotale(oc.getPrezzoTotale())
				.quantita(oc.getQuantita())
				.carrelloId(oc.getCarrello().getId())
		        .itemId(oc.getItem().getId())
				.build();
	}
	
	public static CarrelliDTO buildCarrelliDTO(Carrelli carrelli) {
		return CarrelliDTO.builder()
		        .id(carrelli.getId())
		        .cliente(ClientiDTO.builder()
		                .id(carrelli.getCliente().getId())
		                .email(carrelli.getCliente().getEmail())
		                .nome(carrelli.getCliente().getNome())
		                .cognome(carrelli.getCliente().getCognome())
		                .indirizzo(carrelli.getCliente().getIndirizzo())
		                //aggiungere altri campi cliente (?)
		                .build())
		        .oggettiCarrello(carrelli.getOggettiCarrello().stream()
		                .map(oc -> OggettiCarrelliDTO.builder()
		                        .id(oc.getId())
		                        .quantita(oc.getQuantita())
		                        .prezzoTotale(oc.getPrezzoTotale())
		                        .build())
		                .toList())
		        .build();
	}
	
	public static MetodiPagamentoDTO buildMetodiPagamentoDTO(MetodiPagamento mp) {
		return MetodiPagamentoDTO.builder()
				.id(mp.getId())
				.nome(mp.getNome())
				.provider(mp.getProvider())
				.build();
	}
	
	public static CouponsDTO buildCouponsDTO(Coupons c) {
		return CouponsDTO.builder()
				.id(c.getId())
                .codice(c.getCodice())
                .tipo(c.getTipo().toString())
                .valore(c.getValore())
                .attivo(c.getAttivo())
                .dataInizio(c.getDataInizio())
                .dataFine(c.getDataFine())
                .build();
	}
}
