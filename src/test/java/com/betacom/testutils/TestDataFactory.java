package com.betacom.testutils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import com.betacom.enums.Roles;
import com.betacom.enums.StatoPagamento;
import com.betacom.enums.TipoCoupon;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.Recensioni;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;
import com.betacom.persistence.entity.commerce.items.Categorie;
import com.betacom.persistence.entity.commerce.items.Prodotti;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.IRecensioniRepository;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.persistence.repository.commerce.checkout.ICouponsRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOggettiOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IPagamentiRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiGiornataRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.ICategorieRepository;
import com.betacom.persistence.repository.commerce.items.IProdottiRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.utilities.Utils;

import jakarta.transaction.Transactional;

public class TestDataFactory {

    public static Utenti creaUtenteValido(IUtentiRepository utR) {

    	String unique = String.valueOf(System.currentTimeMillis());
        Utenti u = new Utenti();
        u.setUserName("testuser" + unique);
        u.setEmail("test" + unique + "@mail.com");
        u.setPwd("1234");
        u.setRole(Roles.valueOf("USER"));

        return utR.save(u); 
    }
    
    public static Utenti creaUtenteValido(IUtentiRepository utR, String invalido) {

        Utenti u = new Utenti();
        u.setUserName("testuser" + invalido);
        u.setEmail("test" + invalido + "@mail.com");
        u.setPwd("1234");
        u.setRole(Roles.valueOf("USER"));

        return utR.save(u); 
    }
    
    public static Prodotti creaProdottoValido(IProdottiRepository prR, ICategorieRepository catR) {
        Prodotti p = new Prodotti();

        Categorie c = creaCategoriaValida(catR, String.valueOf(System.currentTimeMillis()));
        p.setCategoria(c);

        p.setDescrizione("TestDescrizione");
        p.setDimensioni(new BigDecimal("10.00"));
        p.setNome("TestNome");
        p.setPeso(new BigDecimal("10.00"));
        p.setPrezzo(new BigDecimal("10.00"));

        long sku = ThreadLocalRandom.current().nextLong(100000L, 9999999999L);
        p.setSku(sku);

        p.setStock(1);
        p.setUrlImmagine("URL IMAGE TEST");

        return prR.save(p);
    }
    

    public static Clienti creaClienteValido(
            IClientiRepository clR, 
            IUtentiRepository utR) {

        Utenti utente = creaUtenteValido(utR);

        Clienti c = new Clienti();

        c.setNome("Mario");
        c.setCognome("Rossi");
        c.setIndirizzo("Via Roma 1");
        c.setUtente(utente);
        c.setComune("Roma");
        c.setCap("00100");
        c.setTelefono("3331234567");
        c.setProvinca("RM");
        return clR.save(c);
    }


    public static Ordini creaOrdineValido(
            IOrdiniRepository ordR, 
            IClientiRepository clR, 
            IUtentiRepository utR) {

    	Clienti cliente = creaClienteValido(clR, utR);

        Ordini o = new Ordini();
        o.setCliente(cliente);
        o.setNome(cliente.getNome());
        o.setCognome(cliente.getCognome());
        o.setIndirizzo(cliente.getIndirizzo());
        
        return ordR.save(o);
    }

    public static Pagamenti creaPagamentoValido(
    		IPagamentiRepository pagR,
    		IOrdiniRepository ordR,
    		IClientiRepository clR,
    		IUtentiRepository utR,
    		IMetodiPagamentiRepository mpR
            ) {
    	
        Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        MetodiPagamento metodo = TestDataFactory.creaMetodoPagamentoValido(mpR);

    	Pagamenti p = new Pagamenti();
    	p.setOrdine(ordine);
        p.setMetodoPagamento(metodo);
        p.setImporto(BigDecimal.valueOf(150));
        p.setStato(StatoPagamento.valueOf("ATTESA"));
        
        return pagR.save(p);
    }

    public static Corrieri creaCorriereValido(ICorrieriRepository corR) {
    	
    	String unique = String.valueOf(System.currentTimeMillis());
    	Corrieri c = new Corrieri();
        c.setNome("DHL" + unique);
        return corR.save(c);
    }
    
    public static MetodiPagamento creaMetodoPagamentoValido(
    		IMetodiPagamentiRepository mpR
    		) {
    	String unique = String.valueOf(System.currentTimeMillis());
    	MetodiPagamento mp = new MetodiPagamento();
    	mp.setNome("Paypal" + unique);
    	mp.setProvider("Paypal");
    	
    	return mpR.save(mp);
    }

    public static TipiBiglietti creaTipoBigliettoValido(ITipiBigliettiRepository tipiR) {
        TipiBiglietti t = new TipiBiglietti();
        t.setNome("Standard");
        return tipiR.save(t);
    }
    
    public static Biglietti creaBigliettoValido(
    		IBigliettiRepository bigR,
    		ITipiBigliettiRepository tipiR
    		) {
    	TipiBiglietti t = creaTipoBigliettoValido(tipiR);
    	
        Biglietti b = new Biglietti();
        b.setNome("Biglietto Intero");
        b.setDescrizione("Ingresso standard adulti");
        b.setUrlImmagine("biglietto.png");
        b.setPrezzo(new BigDecimal("15.50"));
        b.setTipo(t);
        
        return bigR.save(b);
    }
    
    public static BigliettiGiornata creaBigliettoGiornataValido(
    		 IGiornateRepository gioR,
    		 IEventiRepository evR,
    		 ITipiBigliettiRepository tipiR,
    		 IBigliettiRepository bigR,
    		 IBigliettiGiornataRepository biGR
    		) {
    	Giornate giornata = TestDataFactory.creaGiornataValida(gioR, evR);
        Biglietti biglietto = TestDataFactory.creaBigliettoValido(bigR, tipiR);
        Eventi evento = giornata.getEvento();
    	
    	BigliettiGiornata big = new BigliettiGiornata();
        big.setGiornata(giornata);
        big.setBiglietto(biglietto);
        big.setEvento(evento);
        big.setPrezzo(BigDecimal.valueOf(60));
        big.setStock(50);
        
        return biGR.save(big);
    }
    
    public static Coupons creaCouponValido(ICouponsRepository couR) {
    	
    	String unique = String.valueOf(System.currentTimeMillis());
    	Coupons c = new Coupons();
    	
    	c.setCodice("promo" + unique);
    	c.setValore(BigDecimal.valueOf(10));
    	c.setTipo(TipoCoupon.valueOf("FISSO"));
        c.setAttivo(true);
        c.setDataInizio(LocalDate.now());
        c.setDataFine(LocalDate.now().plusMonths(1));
    	
		return couR.save(c);
    }
    
    
    public static Categorie creaCategoriaValida(
    		ICategorieRepository catR,
    		String unique
    		) {

    	Categorie c = new Categorie();
    	
    	c.setNome("Categoria" + unique);
    	
		return catR.save(c);
    }

    @Transactional
    public static Carrelli creaCarrelloValido(
            ICarrelliRepository carrelliR,
            IClientiRepository clR,
            IUtentiRepository utR) {

        Clienti cliente = creaClienteValido(clR, utR);

        Carrelli carrello = new Carrelli();
        carrello.setCliente(cliente);

        return carrelliR.save(carrello);
    }

    public static Eventi creaEventoValido(IEventiRepository evR) {
        Eventi e = new Eventi();
        e.setTipoEvento("Standard");
        e.setDataInizio(LocalDate.now());
        return evR.save(e);
    }

    public static Giornate creaGiornataValida(IGiornateRepository gioR, IEventiRepository evR) {
        Eventi e = creaEventoValido(evR);
        Giornate g = new Giornate();
        g.setData(LocalDate.now());
        g.setEvento(e);
        return gioR.save(g);
    }
    
    public static OggettiOrdini creaOggettiOrdiniValido(
    		IOggettiOrdiniRepository ooR,
    		IOrdiniRepository ordR,
    		IClientiRepository clR,
    		IUtentiRepository utR,
    		ITipiBigliettiRepository tipiR,
    		IBigliettiRepository bigliettoR
    		) {
    	Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
    	Biglietti item = TestDataFactory.creaBigliettoValido(bigliettoR, tipiR);

        OggettiOrdini oo = new OggettiOrdini();
        oo.setOrdine(ordine);
        oo.setItem(item);
        oo.setQuantita(2);
        oo.setPrezzoUnitario(item.getPrezzo());
        oo.setPrezzoTotale(Utils.calcolaPrezzoTotale(oo.getQuantita(),oo.getPrezzoUnitario()));
        
        return ooR.save(oo);
    }


}

