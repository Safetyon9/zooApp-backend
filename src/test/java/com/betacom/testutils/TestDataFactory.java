package com.betacom.testutils;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.betacom.dto.inputs.*;
import com.betacom.dto.inputs.commerce.*;
import com.betacom.enums.*;
import com.betacom.persistence.entity.*;
import com.betacom.persistence.entity.commerce.*;
import com.betacom.persistence.entity.commerce.checkout.*;
import com.betacom.persistence.repository.*;
import com.betacom.persistence.repository.commerce.*;
import com.betacom.persistence.repository.commerce.checkout.*;

public class TestDataFactory {

    public static Utenti creaUtenteValido(IUtentiRepository utR) {
        UtentiReq req = new UtentiReq();
        req.setUsername("testuser");
        req.setEmail("test@mail.com");
        req.setPwd("1234");
        req.setRole("USER");

        Utenti u = new Utenti();
        u.setUserName(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPwd(req.getPwd());
        u.setRole(Roles.valueOf(req.getRole()));

        return utR.save(u);
    }

    public static Clienti creaClienteValido(
            IClientiRepository clR, 
            IUtentiRepository utR) {

        Utenti utente = creaUtenteValido(utR);

        ClientiReq req = new ClientiReq();
        req.setNome("Mario");
        req.setCognome("Rossi");
        req.setIndirizzo("Via Roma 1");
        req.setUtenteUsername(utente.getUserName());
        req.setComune("Roma");
        req.setCap("00100");
        req.setTelefono("3331234567");
        req.setProvincia("RM");

        Clienti c = new Clienti();
        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());
        c.setUtente(utente);
        c.setComune(req.getComune());
        c.setCap(req.getCap());
        c.setTelefono(req.getTelefono());
        c.setProvinca(req.getProvincia());

        return clR.save(c);
    }


    public static Ordini creaOrdineValido(
            IOrdiniRepository ordR, 
            IClientiRepository clR, 
            IUtentiRepository utR) {

    	Clienti cliente = creaClienteValido(clR, utR);

        Ordini o = new Ordini();
        o.setCliente(cliente);
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
}