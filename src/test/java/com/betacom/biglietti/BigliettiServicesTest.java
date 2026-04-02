package com.betacom.biglietti;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.services.interfaces.commerce.items.IBigliettiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BigliettiServicesTest {
	
	@Autowired
	private IBigliettiServices bigliettiS;

	@Autowired
	private ITipiBigliettiRepository tipiR;
	
	@Test
	@Order(1)
	public void createBigliettoTest() {
		TestDataFactory.creaTipoBigliettoValido(tipiR);
		log.debug("create biglietto");
		try {
			BigliettiReq req = new BigliettiReq();
			req.setNome("Biglietto Standard");
			req.setDescrizione("Ingresso base");
			req.setPrezzo(new BigDecimal("10.00"));
			req.setTipoId(1);
		
			bigliettiS.create(req);
			
			List<BigliettiDTO> lB = bigliettiS.findAll();
			BigliettiDTO createBiglietto = lB.stream()
					.filter(b -> "Biglietto Standard".equals(b.getNome()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Biglietto non trovato"));

			Assertions.assertThat(createBiglietto.getNome()).isEqualTo("Biglietto Standard");
			
			req = new BigliettiReq();
			req.setNome("Biglietto Famiglia");
			req.setDescrizione("Ingresso per 4 persone");
			req.setPrezzo(new BigDecimal("35.00"));
			req.setTipoId(1);
		
			bigliettiS.create(req);
			
			lB = bigliettiS.findAll();
			createBiglietto = lB.stream()
					.filter(b -> "Biglietto Famiglia".equals(b.getNome()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Biglietto non trovato: Biglietto Famiglia" ));

			Assertions.assertThat(createBiglietto.getNome()).isEqualTo("Biglietto Famiglia");
			
			lB.forEach(b -> log.debug(b.toString()));
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	public void createBigliettoErrorTest() {
		log.debug("create biglietto in error");
		
		BigliettiReq req = new BigliettiReq();
	
		assertThrows(Exception.class, () -> {
			bigliettiS.create(req);
		});
	}
}
