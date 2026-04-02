package com.betacom.giornate;

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

import com.betacom.dto.inputs.commerce.GiornateReq;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.services.interfaces.commerce.IGiornateServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GiornateServicesTest {

	@Autowired
	private IEventiRepository evRepo;
	
	@Autowired
	private IGiornateServices giornateS;
	
	@Test
	@Order(1)
	public void createGiornataTest() {
		TestDataFactory.creaEventoValido(evRepo);
		log.debug("create giornata");
		try {
			GiornateReq req = new GiornateReq();
			req.setData(LocalDate.of(2025, 8, 15));
			req.setEventoId(1);
		
			giornateS.create(req);
			
			List<GiornateDTO> lG = giornateS.findAll();
			GiornateDTO createGiornata = lG.stream()
					.filter(g -> LocalDate.of(2025, 8, 15).equals(g.getData()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Giornata non trovata"));

			Assertions.assertThat(createGiornata.getEventoId()).isEqualTo(1);
			
			lG.forEach(g -> log.debug(g.toString()));
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	public void createGiornataErrorTest() {
		log.debug("create giornata in error");
		
		GiornateReq req = new GiornateReq();
	
		assertThrows(Exception.class, () -> {
			giornateS.create(req);
		});
	}
}
