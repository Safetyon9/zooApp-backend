package com.betacom.spedizioni;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.services.interfaces.commerce.checkout.ISpedizioniServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioniImplTest {
	
	@Autowired
	private ISpedizioniServices spedS;
	
	@Autowired
    private IOrdiniRepository ordR;

    @Autowired
    private ICorrieriRepository corR;
	
	@Test
	@Order(1)
	public void createSpedizioneTest() {
		log.debug("create spedizione");
		try {
			SpedizioniReq req = new SpedizioniReq();
			req.setCorriereId(null);
			req.setTrackingNumber(null);
		    req.setCosto(null);
		    req.setStato(null);
		    req.setDataAggiornamento(null);
		    req.setOrdineId(null);
		
			spedS.create(req);
			
			List<SpedizioniDTO> lS = spedS.list();
			SpedizioniDTO createSpedizione = lS.stream()
//					.filter(s -> "PV001".equals(s.getCodiceFiscale()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Spedizione non trovata"));

//			Assertions.assertThat(createSpedizione.getCognome()).isEqualTo("Verde");
			
			req = new SpedizioniReq();
		
			spedS.create(req);
			
			lS = spedS.list();
			createSpedizione = lS.stream()
//					.filter(s -> "AR001".equals(s.getCodiceFiscale()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Socio non trovato:AR001" ));

//			Assertions.assertThat(createSpedizione.getCognome()).isEqualTo("Rossi");
			
			lS.forEach(s -> log.debug(s.toString()));
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
	}
	
	@Test
	@Order(2)
	public void createSpedizioneErrorTest() {
		log.debug("create spedizione in error");
		
		SpedizioniReq req = new SpedizioniReq();
		req.setCorriereId(null);
		req.setTrackingNumber(null);
	    req.setCosto(null);
	    req.setStato(null);
	    req.setDataAggiornamento(null);
	    req.setOrdineId(null);
	
		assertThrows(Exception.class, () -> {
			spedS.create(req);
		});
		
	}
	
    private Ordini creaOrdineValido() {
    	Ordini o = new Ordini();
        o.setIndirizzo("Via Roma 1");
        return ordR.save(o);
    }

    private Corrieri creaCorriereValido() {
    	Corrieri c = new Corrieri();
        c.setNome("DHL");
        return corR.save(c);
    }
}