package com.betacom.eventi;

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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.betacom.controllers.commerce.EventiController;
import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventiControllerTest {
	
	@MockitoBean
	private IMailServices mailServices;
	
	@Autowired
	private EventiController eventiC;
	
	@Autowired
	private IEventiRepository evRepo;
	
	@Autowired
	private IMessaggiServices msgS;
	
	@Test
	@Order(1)		
	public void createEvento() {
		log.debug("Create evento");
		EventiReq req = new EventiReq();
		req.setTipoEvento("Visita Guidata");
		req.setDataInizio(LocalDate.now());
		req.setDataFine(LocalDate.now().plusDays(5));
		
		ResponseEntity<Resp> resp = eventiC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
	}

	@Test
	@Order(2)		
	public void getEvento() {
		log.debug("Test getEvento");
		ResponseEntity<?> resp = eventiC.getById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		EventiDTO dto = (EventiDTO)resp.getBody();
		Assertions.assertThat(dto.getTipoEvento()).isEqualTo("Visita Guidata");
	}

	@Test
	@Order(3)	
	public void getEventoError() {
		log.debug("Test getEvento error");
		ResponseEntity<?> resp = eventiC.getById(9999);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(4)	
	public void updateEvento() {
		log.debug("Update evento");
		
		EventiReq req = new EventiReq();
		req.setId(1);
		req.setTipoEvento("Visita Guidata Serale");
		req.setDataInizio(LocalDate.now());
		req.setDataFine(LocalDate.now().plusDays(7));
		
		ResponseEntity<Resp> resp = eventiC.update(req);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
	}

	@Test
	@Order(5)	
	public void deleteEvento() {
		log.debug("delete evento");
		
		ResponseEntity<Resp> resp = eventiC.delete(1);
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
	}
	
	@Test
	@Order(6)	
	public void list() {
		log.debug("Test list eventi");
		
		ResponseEntity<?> resp = eventiC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<EventiDTO> lE = (List<EventiDTO>) body;
		
		Assertions.assertThat(lE.size()).isGreaterThanOrEqualTo(0);
		lE.forEach(e -> log.debug(e.toString()));
	}
	
	@Test
	@Order(6)	
	public void find() {
		log.debug("Test find eventi");
		
		EventiReq req = new EventiReq();
		req.setTipoEvento("Visita Guidata");
		req.setDataInizio(LocalDate.now());
		req.setDataFine(LocalDate.now().plusDays(5));
		
		ResponseEntity<?> resp = eventiC.find(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<EventiDTO> lE = (List<EventiDTO>) body;
		
		Assertions.assertThat(lE.size()).isGreaterThanOrEqualTo(0);
		lE.forEach(e -> log.debug(e.toString()));
	}

}
