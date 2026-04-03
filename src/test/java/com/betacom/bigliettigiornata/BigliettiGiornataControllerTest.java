package com.betacom.bigliettigiornata;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.items.BigliettiGiornataController;
import com.betacom.dto.inputs.commerce.items.BigliettiGiornateReq;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BigliettiGiornataControllerTest {

    @Autowired
    private BigliettiGiornataController bigGiornC;

    @Autowired
    private IGiornateRepository gioR;

    @Autowired
    private IEventiRepository evR;

    @Autowired
    private ITipiBigliettiRepository tipiR;
    
    @Autowired
    private IBigliettiRepository bigR;
    
    @Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createBigliettoGiornataTest() {
        log.debug("create biglietto giornata");

        Giornate giornata = TestDataFactory.creaGiornataValida(gioR, evR);
        Biglietti biglietto = TestDataFactory.creaBigliettoValido(bigR, tipiR);
        Eventi evento = giornata.getEvento();

        BigliettiGiornateReq req = new BigliettiGiornateReq();
        req.setGiornataId(giornata.getId());
        req.setBigliettoId(biglietto.getId());
        req.setEventoId(evento.getId());
        req.setPrezzo(BigDecimal.valueOf(50));
        req.setStock(100);

        ResponseEntity<Resp> resp = bigGiornC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createBigliettoGiornataErrorTest() {
        log.debug("create biglietto giornata error");

        BigliettiGiornateReq req = new BigliettiGiornateReq();
        req.setGiornataId(9999);
        req.setBigliettoId(9999);
        req.setEventoId(9999);
        req.setPrezzo(BigDecimal.valueOf(30));
        req.setStock(10);

        ResponseEntity<Resp> resp = bigGiornC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg())
        .isEqualTo("Biglietto non trovato");
    }

    @Test
    @Order(3)
    public void findByIdTest() {
        Giornate giornata = TestDataFactory.creaGiornataValida(gioR, evR);
        TipiBiglietti biglietto = TestDataFactory.creaTipoBigliettoValido(tipiR);
        Eventi evento = giornata.getEvento();

        BigliettiGiornateReq req = new BigliettiGiornateReq();
        req.setGiornataId(giornata.getId());
        req.setBigliettoId(biglietto.getId());
        req.setEventoId(evento.getId());
        req.setPrezzo(BigDecimal.valueOf(60));
        req.setStock(50);

        bigGiornC.create(req);

        ResponseEntity<?> resp = bigGiornC.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        BigliettiGiornateDTO dto = (BigliettiGiornateDTO) resp.getBody();
        Assertions.assertThat(dto.getPrezzo()).isEqualTo(BigDecimal.valueOf(60));
        Assertions.assertThat(dto.getStock()).isEqualTo(50);
    }

    @Test
    @Order(4)
    public void findByIdErrorTest() {
        ResponseEntity<?> resp = bigGiornC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("BigliettoGiornata non trovato");
    }

    @Test
    @Order(5)
    public void updateBigliettoGiornataTest() {
        Giornate giornata = TestDataFactory.creaGiornataValida(gioR, evR);
        TipiBiglietti biglietto = TestDataFactory.creaTipoBigliettoValido(tipiR);
        Eventi evento = giornata.getEvento();

        BigliettiGiornateReq req = new BigliettiGiornateReq();
        req.setGiornataId(giornata.getId());
        req.setBigliettoId(biglietto.getId());
        req.setEventoId(evento.getId());
        req.setPrezzo(BigDecimal.valueOf(70));
        req.setStock(30);

        bigGiornC.create(req);

        BigliettiGiornateReq updateReq = new BigliettiGiornateReq();
        updateReq.setId(1);
        updateReq.setPrezzo(BigDecimal.valueOf(80));
        updateReq.setStock(40);

        ResponseEntity<Resp> resp = bigGiornC.update(updateReq);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(6)
    public void deleteBigliettoGiornataTest() {
        Giornate giornata = TestDataFactory.creaGiornataValida(gioR, evR);
        TipiBiglietti biglietto = TestDataFactory.creaTipoBigliettoValido(tipiR);
        Eventi evento = giornata.getEvento();

        BigliettiGiornateReq req = new BigliettiGiornateReq();
        req.setGiornataId(giornata.getId());
        req.setBigliettoId(biglietto.getId());
        req.setEventoId(evento.getId());
        req.setPrezzo(BigDecimal.valueOf(90));
        req.setStock(25);

        bigGiornC.create(req);

        ResponseEntity<Resp> resp = bigGiornC.delete(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }
    
    @Test
	@Order(7)	
	public void list() {
    	log.debug("Test list biglietto giornata");
		
		ResponseEntity<?> resp = bigGiornC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<BigliettiGiornateDTO> lS = (List<BigliettiGiornateDTO>) body;
		
		Assertions.assertThat(lS).isNotEmpty();

		BigliettiGiornateDTO first = lS.get(0);

		Assertions.assertThat(first.getPrezzo()).isNotNull();
		Assertions.assertThat(first.getStock()).isGreaterThan(0);
		Assertions.assertThat(first.getBigliettoId()).isNotNull();
		Assertions.assertThat(first.getGiornataId()).isNotNull();
		lS.forEach(s -> log.debug(s.toString()));
	}
}