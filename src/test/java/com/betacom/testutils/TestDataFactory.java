package com.betacom.testutils;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.betacom.enums.Roles;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.ISpedizioniRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;

public class TestDataFactory {

    public static Utenti creaUtenteValido(IUtentiRepository utR) {

        Utenti u = new Utenti();
        u.setUserName("testuser");
        u.setEmail("test@mail.com");
        u.setPwd("1234");
        u.setRole(Roles.valueOf("USER"));

        return utR.save(u);
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

    public static Corrieri creaCorriereValido(ICorrieriRepository corR) {
    	Corrieri c = new Corrieri();
        c.setNome("DHL");
        return corR.save(c);
    }

    public static Spedizioni creaSpedizioneValida(
            ISpedizioniRepository speR,
            IOrdiniRepository ordR,
            IClientiRepository clR,
            IUtentiRepository utR,
            ICorrieriRepository corR) {

        Ordini ordine = creaOrdineValido(ordR, clR, utR);
        Corrieri corriere = creaCorriereValido(corR);

        Spedizioni s = new Spedizioni();
        s.setOrdine(ordine);
        s.setCorriere(corriere);
        s.setTrackingNumber("TRACK123");
        s.setCosto(BigDecimal.valueOf(10));
        s.setDataAggiornamento(LocalDate.now());

        return speR.save(s);
    }
    
    public static MetodiPagamento creaMetodoPagamentoValido(
    		IMetodiPagamentiRepository mpR
    		) {
    	MetodiPagamento mp = new MetodiPagamento();
    	mp.setNome("Paypal");
    	mp.setProvider("Paypal");
    	
    	return mpR.save(mp);
    }

    public static TipiBiglietti creaTipoBigliettoValido(ITipiBigliettiRepository tipiR) {
        TipiBiglietti t = new TipiBiglietti();
        t.setNome("Standard");
        return tipiR.save(t);
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
}