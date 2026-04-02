package com.betacom.carrelli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.CarrelliController;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.response.Resp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelliControllerTest {
	
	private final CarrelliController carrelliC;
	
	@SuppressWarnings("unchecked")
	@Test
	@Order(1)	
	public void myTest() {
		getCarrello();
		getCarrelloError();
		create();
		update();
		delete();
		list();
	}
	
	public void getCarrello() {
		log.debug("Test getCarrello");
		ResponseEntity<?> resp = carrelliC.getById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		CarrelliDTO car = (CarrelliDTO)resp.getBody();
		Assertions.assertThat(car.getCliente().getNome()).isEqualTo("Paolo");
	}
	public void getCarrelloError() {
		log.debug("Test getCarrello error");
		ResponseEntity<?> resp = carrelliC.getById(99);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertThat(resp.getBody()).isEqualTo("Socio non trovato nel DB...99");
	}

	public void create() {

		log.debug("Create");
		SocioReq req = new SocioReq();
		req.setNome("Anna");
		req.setCognome("LaBella");
		req.setCodiceFiscale("AB009");
		req.setMail("a.bella@gmail.com");
		
		ResponseEntity<Resp> resp = socioC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}

	public void list() {
		log.debug("Test list");
		
		ResponseEntity<?> resp = socioC.list(null, null, null, null);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<SocioDTO> lS = (List<SocioDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
//	Assertions.assertThat(lS.get(0).getCognome()).isEqualTo("Rossi");
		lS.forEach(s -> log.debug(s.toString()));
// updateSocio();
	}
	
	public void update() {
		log.debug("*** Update ***");
		
		SocioReq req = new SocioReq();
		req.setId(3);
		req.setCognome("LaBrutta");
		
		ResponseEntity<Resp> resp = socioC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
		
				
	}

	public void delete() {
		log.debug("*** delete ***");
		
		
		ResponseEntity<Resp> resp = socioC.delete(3);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");			
	}

}