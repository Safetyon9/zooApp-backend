package com.betacom.utilities;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.dto.outputs.RegisterDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.UtentiResp;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.dto.outputs.commerce.RecensioniDTO;
import com.betacom.dto.outputs.commerce.checkout.CorrieriDTO;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;
import com.betacom.dto.outputs.commerce.checkout.MetodiPagamentoDTO;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.dto.outputs.commerce.items.CategorieDTO;
import com.betacom.dto.outputs.commerce.items.ProdottiDTO;
import com.betacom.dto.outputs.commerce.items.TipiBigliettiDTO;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.OggettiCarrelli;
import com.betacom.persistence.entity.commerce.Recensioni;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;
import com.betacom.persistence.entity.commerce.items.Categorie;
import com.betacom.persistence.entity.commerce.items.Prodotti;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;

public class Mapper {

	public static BigliettiDTO buildBigliettiDTO(Biglietti b){
	    return BigliettiDTO.builder()
	            .id(b.getId())
	            .nome(b.getNome())
	            .descrizione(b.getDescrizione())
	            .urlImmagine(b.getUrlImmagine())
	            .prezzo(b.getPrezzo())
	            .tipoNome(b.getTipo() != null ? b.getTipo().getNome() : null)
	            .build();
	}

	public static ClientiDTO buildClienteDTO(Clienti c){
	    return ClientiDTO.builder()
	            .id(c.getId())
	            .nome(c.getNome())
	            .cognome(c.getCognome())
	            .indirizzo(c.getIndirizzo())
	            .comune(c.getComune())
	            .cap(c.getCap())
	            .telefono(c.getTelefono())
	            .utenteUsername(c.getUtente() != null ? c.getUtente().getUserName() : null)
	            .carrelloId(c.getCarrello() != null ? c.getCarrello().getId() : null)
	            .build();
	}
	
	public static RegisterDTO buildRegisterDTO(Clienti c, Utenti u) {
		return RegisterDTO.builder()
				.id(c.getId())
	            .nome(c.getNome())
	            .cognome(c.getCognome())
	            .indirizzo(c.getIndirizzo())
	            .cap(c.getCap())
	            .comune(c.getComune())
	            .provincia(c.getProvinca())
	            .telefono(c.getTelefono())
	            .carrelloId(c.getCarrello() != null ? c.getCarrello().getId() : null)
	            .userName(u.getUserName())
			    .email(u.getEmail())
			    .role(u.getRole().name())
	            
	            .build();
	}
	
	public static EventiDTO buildEventoDTO(Eventi e){
	    return EventiDTO.builder()
	            .id(e.getId())
	            .tipoEvento(e.getTipoEvento())
	            .dataInizio(e.getDataInizio())
	            .dataFine(e.getDataFine())
	            .descrizione(e.getDescrizione())
	            .build();
	}
	
	public static CategorieDTO buildCategorieDTO(Categorie c){
	    return CategorieDTO.builder()
	            .id(c.getId())
	            .nome(c.getNome())
	            .build();
	}
	
	public static TipiBigliettiDTO buildTipiBigliettiDTO(TipiBiglietti t) {
        return TipiBigliettiDTO.builder()
                .id(t.getId())
                .nome(t.getNome())
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
                .ordineId(oo.getOrdine()!= null ? oo.getOrdine().getId() : null)
                .build();
    }
	
	public static List<OggettiOrdiniDTO> buildOgettiOrdiniDTO(List<OggettiOrdini> lO) {
	    return lO.stream()
	            .map(oo -> buildOgettiOrdiniDTO(oo)
	            )
	            .collect(Collectors.toList());
	}

	
	public static OrdiniDTO buildOrdiniDTO(Ordini o) {
		return OrdiniDTO.builder()
		        .id(o.getId())
		        .clienteId(o.getCliente() != null ? o.getCliente().getId() : null)
		        .nome(o.getNome())
		        .cognome(o.getCognome())
		        .indirizzo(o.getIndirizzo())
		        .stato(o.getStato())
		        .dataOrdine(o.getDataOrdine())
		        .righe(
		            o.getOggettiOrdine() != null 
		                ? buildOgettiOrdiniDTO(o.getOggettiOrdine()) 
		                : Collections.emptyList()
		        )
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
                .nome(p.getNome())
                .descrizione(p.getDescrizione())
                .urlImmagine(p.getUrlImmagine())
                .prezzo(p.getPrezzo())
                .dimensioni(p.getDimensioni())
                .peso(p.getPeso())
                .stock(p.getStock())
                .sku(p.getSku())
                .categoriaNome(p.getCategoria() != null ? p.getCategoria().getNome() : null)
                .build();
		
	}
	
	public static UtentiDTO buildUtentiDTO(Utenti u) {
		return UtentiDTO.builder()
			    .userName(u.getUserName())
			    .email(u.getEmail())
			    .role(u.getRole().name())
			    .isActive(u.getIsActive())
			    .isValidate(u.getIsValidate())
			    .validationToken(u.getValidationToken())
			    .build();
		
	}
	
	public static UtentiResp buildUtentiResp(Utenti u, Clienti c) {
		if (c == null) {
			return UtentiResp.builder()
				    .userName(u.getUserName())
				    .email(u.getEmail())
				    .role(u.getRole().name())
				    .isValidate(u.getIsValidate())
				    .build();
		}
			
		return UtentiResp.builder()
			    .userName(u.getUserName())
			    .email(u.getEmail())
			    .role(u.getRole().name())
			    .nome(c.getNome())
			    .cognome(c.getCognome())
			    .indirizzo(c.getIndirizzo())
			    .comune(c.getComune())
			    .cap(c.getCap())
			    .telefono(c.getTelefono())
			    .provincia(c.getProvinca())
			    .isValidate(u.getIsValidate())
			    .carrelloId(c.getCarrello() != null ? c.getCarrello().getId() : null)
			    .build();
		
	}
	
	public static PagamentiDTO buildPagamentoDTO(Pagamenti p){
	    return PagamentiDTO.builder()
	            .id(p.getId())
	            .importo(p.getImporto())
	            .stato(p.getStato().toString())
	            .dataCreazione(p.getDataCreazione())
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
                .corriereId(s.getCorriere() != null ? s.getCorriere().getId() : null)
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
				.prezzoUnitario(oc.getPrezzoUnitario())			
				.prezzoTotale(oc.getPrezzoTotale())
				.quantita(oc.getQuantita())
				.carrelloId(oc.getCarrello().getId())
		        .itemId(oc.getItem().getId())
				.build();
	}
	
	public static List<OggettiCarrelliDTO> buildOggettiCarrelliDTO(List<OggettiCarrelli> lOC) {
	    return lOC.stream()
	              .map(Mapper::buildOggettiCarrelliDTO)
	              .collect(Collectors.toList());
	}
	
	public static CarrelliDTO buildCarrelliDTO(Carrelli carrelli) {
	    return CarrelliDTO.builder()
	            .id(carrelli.getId())
	            .clienteId(carrelli.getCliente() != null ? carrelli.getCliente().getId() : null)
	            .prezzoTotale(calcolaPrezzoTot(carrelli.getOggettiCarrello()))
	            .oggettiCarrello(
	                carrelli.getOggettiCarrello() != null 
	                    ? buildOggettiCarrelliDTO(carrelli.getOggettiCarrello())
	                    : Collections.emptyList()
	            )
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
	
	public static CorrieriDTO buildCorrieriDTO(Corrieri c) {
		return CorrieriDTO.builder()
				.id(c.getId())
                .nome(c.getNome())
                .build();
	}
	
	private static BigDecimal calcolaPrezzoTot(List<OggettiCarrelli> o) {
	    BigDecimal totale = BigDecimal.ZERO;

	    for (OggettiCarrelli oggetto : o) {
	        if (oggetto != null && oggetto.getPrezzoTotale() != null) {
	            totale = totale.add(oggetto.getPrezzoTotale());
	        }
	    }

	    return totale;
	}

}
