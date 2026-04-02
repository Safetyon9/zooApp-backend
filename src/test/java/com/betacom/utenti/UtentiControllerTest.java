package com.betacom.utenti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.UtentiController;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiControllerTest {
	
	@Autowired
	private UtentiController utentiC;
	
	@Test
	@Order(1)
	public void getUtenti() {
		log.debug("Test getSocio");
		ResponseEntity<?> resp = utentiC.findByUserName("Test1");
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		UtentiDTO ute = (UtentiDTO) resp.getBody();
		Assertions.assertThat(ute.getEmail()).isEqualTo("test@email.com");
	
	}
	
	@Test
	@Order(2)
	public void getUtentiError() {
		log.debug("Test getSocio");
		ResponseEntity<?> resp = utentiC.findByUserName("Test1000");
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertThat(resp.getBody()).isEqualTo("Utente non trovato in db:Test1000");
	
	}
	
	@Test
	@Order(3)	
	public void createSocio() {

		log.debug("Create socio");
		UtentiReq req = new UtentiReq();
		req.setUsername("Test3");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("a.bella@gmail.com");
		
		ResponseEntity<?> resp = utentiC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}
	
	@Test
	@Order(4)	
	public void updateUtenti() {
		log.debug("******* Update utenti  *******");
		
		UtentiReq req = new UtentiReq();
		req.setUsername("Test4");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("test@gmail.com");
		
		ResponseEntity<Resp> resp = utentiC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
			
	}
	
	@Test
	@Order(4)	
	public void updateUtentiErr() {
		log.debug("******* Update utenti error *******");
		
		UtentiReq req = new UtentiReq();
		req.setUsername("Test99");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("test@gmail.com");
		
		ResponseEntity<Resp> resp = utentiC.update(req);
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
			
	}
	
	@Test
	@Order(5)	
	public void deleteSocio() {
		log.debug("******* delete utenti  *******");
		
		
		ResponseEntity<Resp> resp = utentiC.delete("Test4");
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");
		
	}
	
	@Test
	@Order(5)	
	public void deleteSocioError() {
		log.debug("******* delete utenti error *******");
		
		
		ResponseEntity<Resp> resp = utentiC.delete("Test400");
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	
	@Test
	@Order(8)	
	public void list() {
		log.debug("Test list socio");
		
		ResponseEntity<?> resp = utentiC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<UtentiDTO> lS = (List<UtentiDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
	//	Assertions.assertThat(lS.get(0).getCognome()).isEqualTo("Rossi");
		lS.forEach(s -> log.debug(s.toString()));
		// updateSocio();
	}
	
	
	
	
	
	
}