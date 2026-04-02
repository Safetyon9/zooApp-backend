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
import com.betacom.dto.inputs.commerce.CarrelliReq;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.response.Resp;

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
//		update();
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
		
		CarrelliReq c = new CarrelliReq();
		
		// c.setCliente(cliente);
		
		ResponseEntity<Resp> resp = carrelliC.create(c);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}

	public void list() {
		log.debug("Test list");
		
		ResponseEntity<?> resp = carrelliC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<CarrelliDTO> lC = (List<CarrelliDTO>) body;
		
		Assertions.assertThat(lC.size()).isGreaterThan(0);
		lC.forEach(s -> log.debug(s.toString()));

	}
	
//	public void update() {
//		log.debug("*** Update ***");
//		
//		CarrelliReq req = new CarrelliReq();
//		req.setId(3);
//		//req.setCognome("LaBrutta");
//		
//		ResponseEntity<Resp> resp = carrelliC.update(req);
//	
//		
//		assertEquals(HttpStatus.OK, resp.getStatusCode());
//		Resp r = (Resp)resp.getBody();
//		log.debug(r.getMsg());
//		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
//		
//				
//	}

	public void delete() {
		log.debug("*** delete ***");
		
		
		ResponseEntity<Resp> resp = carrelliC.delete(3);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");			
	}

}