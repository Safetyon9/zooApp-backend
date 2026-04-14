package com.betacom.biglietti;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.betacom.controllers.commerce.items.BigliettiController;
import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BigliettiControllerTest {
	
	@MockitoBean
	private IMailServices mailServices;
	
	@Autowired
	private BigliettiController bigliettiC;

	@Autowired
	private ITipiBigliettiRepository tipiR;
	
	@Autowired
	private IMessaggiServices msgS;

	@Test
	@Order(1)
	public void createBiglietto() {
		TestDataFactory.creaTipoBigliettoValido(tipiR);
		
		log.debug("Create biglietto");
		BigliettiReq req = new BigliettiReq();
		req.setNome("Biglietto Intero");
		req.setDescrizione("Biglietto ingresso adulti");
		req.setPrezzo(new BigDecimal("15.50"));
		req.setUrlImmagine("test.png");
		req.setTipoId(1);

		ResponseEntity<Resp> resp = bigliettiC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
	}

	@Test
	@Order(2)
	public void getById() {
		log.debug("getById test");
		ResponseEntity<?> resp = bigliettiC.getById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		BigliettiDTO dto = (BigliettiDTO) resp.getBody();
		Assertions.assertThat(dto.getNome()).isEqualTo("Biglietto Intero");
	}

	@Test
	@Order(3)	
	public void updateBiglietto() {
		log.debug("Update biglietto");
		
		BigliettiReq req = new BigliettiReq();
		req.setItemId(1);
		req.setNome("Biglietto Intero Special");
		req.setDescrizione("Biglietto ingresso adulti promo");
		req.setPrezzo(new BigDecimal("12.50"));
		req.setTipoId(1);
		
		ResponseEntity<Resp> resp = bigliettiC.update(req);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
	}

	@Test
	@Order(4)	
	public void updateBigliettoError() {
		log.debug("Update biglietto error");
		
		BigliettiReq req = new BigliettiReq();
		req.setItemId(9999);
		req.setNome("Error");
		
		ResponseEntity<Resp> resp = bigliettiC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(5)	
	public void deleteBiglietto() {
		log.debug("delete biglietto");
		
		ResponseEntity<Resp> resp = bigliettiC.delete(1);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
	}
	
	@Test
	@Order(6)	
	public void deleteBigliettoError() {
		log.debug("delete biglietto error");
		ResponseEntity<Resp> resp = bigliettiC.delete(9999);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());		
	}
	
	@Test
	@Order(6)	
	public void list() {
		log.debug("Test list biglietti");
		
		ResponseEntity<?> resp = bigliettiC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<BigliettiDTO> lB = (List<BigliettiDTO>) body;
		
		Assertions.assertThat(lB.size()).isGreaterThanOrEqualTo(0);
		lB.forEach(b -> log.debug(b.toString()));
	}

}
