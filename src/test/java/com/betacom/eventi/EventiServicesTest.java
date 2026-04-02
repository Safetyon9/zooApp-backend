package com.betacom.eventi;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.services.interfaces.commerce.items.IEventiServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventiServicesTest {
	
	@Autowired
	private IEventiServices eventiS;
	
	@Test
	@Order(1)
	public void createEventoTest() {
		log.debug("create evento");
		try {
			EventiReq req = new EventiReq();
			req.setTipoEvento("Mostra Fotografica");
			req.setDataInizio(LocalDate.of(2025, 5, 1));
			req.setDataFine(LocalDate.of(2025, 5, 15));
		
			eventiS.create(req);
			
			List<EventiDTO> lE = eventiS.findAll();
			EventiDTO createEvento = lE.stream()
					.filter(e -> "Mostra Fotografica".equals(e.getTipoEvento()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Evento non trovato"));

			Assertions.assertThat(createEvento.getTipoEvento()).isEqualTo("Mostra Fotografica");
			
			req = new EventiReq();
			req.setTipoEvento("Safari Notturno");
			req.setDataInizio(LocalDate.of(2025, 6, 1));
			req.setDataFine(LocalDate.of(2025, 6, 2));
		
			eventiS.create(req);
			
			lE = eventiS.findAll();
			createEvento = lE.stream()
					.filter(e -> "Safari Notturno".equals(e.getTipoEvento()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Evento non trovato: Safari Notturno" ));

			Assertions.assertThat(createEvento.getTipoEvento()).isEqualTo("Safari Notturno");
			
			lE.forEach(e -> log.debug(e.toString()));
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	public void createEventoErrorTest() {
		log.debug("create evento in error");
		
		EventiReq req = new EventiReq();
	
		assertThrows(Exception.class, () -> {
			eventiS.create(req);
		});
	}
}
