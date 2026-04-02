package com.betacom.utenti;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.services.interfaces.IUtentiServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiImplTest {
	
	@Autowired
	private IUtentiServices utentiS;
	
	@Test
	@Order(1)
	public void createUtenteTest() {
		log.debug("create socio");
		
		try {
			UtentiReq req = new UtentiReq();
			req.setUsername("Test1");
			req.setPwd("Password");
			req.setRole("ADMIN");
			req.setEmail("test@email.com");
			
			utentiS.create(req);
			
			List<UtentiDTO> lU = utentiS.list();
			UtentiDTO createUtenti = lU.stream()
					.filter(s -> "Test1".equals(s.getUserName()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Utente non trovato"));
			
			Assertions.assertThat(createUtenti.getEmail()).isEqualTo("test@email.com");

			req = new UtentiReq();
			req.setUsername("Test2");
			req.setPwd("Password");
			req.setRole("USER");
			req.setEmail("test2@mail.com");
			
			utentiS.create(req);
			
			lU = utentiS.list();
			createUtenti = lU.stream()
					.filter(s -> "Test2".equals(s.getUserName()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Utente non trovato"));
			
			Assertions.assertThat(createUtenti.getEmail()).isEqualTo("test2@mail.com");
			
			lU.forEach(s -> log.debug(s.toString()));
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	@Test
	@Order(2)
	public void createSocioErrorTest() {
		log.debug("create socio in error");
		
		UtentiReq req = new UtentiReq();
		req.setUsername("Test2");
		req.setPwd("Password");
		req.setRole("USER");
		req.setEmail("test2@mail.com");
	
		assertThrows(Exception.class, () -> {
			utentiS.create(req);
		});
		
	}
}