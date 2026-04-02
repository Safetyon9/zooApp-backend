package com.betacom.giornate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import com.betacom.controllers.commerce.items.GiornateController;
import com.betacom.dto.inputs.commerce.GiornateReq;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.response.Resp;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GiornateControllerTest {
	@Autowired
	private GiornateController giornateC;

	@Autowired
	private IEventiRepository evRepo;

	@Test
	@Order(1)
	public void createGiornata() {
		TestDataFactory.creaEventoValido(evRepo);

		log.debug("Create giornata");
		GiornateReq req = new GiornateReq();
		req.setData(LocalDate.now());
		req.setEventoId(1);

		ResponseEntity<Resp> resp = giornateC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
	}

	@Test
	@Order(2)		
	public void getGiornata() {
		log.debug("Test getGiornata");
		ResponseEntity<?> resp = giornateC.getById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		GiornateDTO g = (GiornateDTO)resp.getBody();
		Assertions.assertThat(g.getEventoId()).isEqualTo(1);
	}

	@Test
	@Order(3)	
	public void getGiornataError() {
		log.debug("Test getGiornata error");
		ResponseEntity<?> resp = giornateC.getById(9999);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(4)	
	public void updateGiornata() {
		log.debug("Update giornata");
		
		GiornateReq req = new GiornateReq();
		req.setId(1);
		req.setData(LocalDate.now().plusDays(1));
		req.setEventoId(1);
		
		ResponseEntity<Resp> resp = giornateC.update(req);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
	}

	@Test
	@Order(5)	
	public void deleteGiornata() {
		log.debug("delete giornata");
		
		ResponseEntity<Resp> resp = giornateC.delete(1);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");
	}
	
	@Test
	@Order(6)	
	public void list() {
		log.debug("Test list giornate");
		
		ResponseEntity<?> resp = giornateC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<GiornateDTO> lG = (List<GiornateDTO>) body;
		
		Assertions.assertThat(lG.size()).isGreaterThanOrEqualTo(0);
		lG.forEach(g -> log.debug(g.toString()));
	}

}
