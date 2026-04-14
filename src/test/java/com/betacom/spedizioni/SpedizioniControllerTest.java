package com.betacom.spedizioni;

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

import com.betacom.config.TestMailConfig;
import com.betacom.controllers.commerce.checkout.SpedizioniController;
import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.ISpedizioniRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@Import(TestMailConfig.class)
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioniControllerTest {

	@Autowired
    private SpedizioniController spedC;
	
	@Autowired
    private IOrdiniRepository ordR;
	
    @Autowired
    private ICorrieriRepository corR;
    
    @Autowired
    private IClientiRepository clR;
    
    @Autowired
    private IUtentiRepository utR;
    
    @Autowired
    private ISpedizioniRepository speR;
    
    @Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createSpedizioneTest() {

        log.debug("create spedizione");

        Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        Corrieri corriere = TestDataFactory.creaCorriereValido(corR);

        SpedizioniReq req = new SpedizioniReq();
        req.setOrdineId(ordine.getId());
        req.setCorriereId(corriere.getId());
        req.setTrackingNumber("TRACK123");
        req.setCosto(BigDecimal.valueOf(10));

        ResponseEntity<Resp> resp = spedC.create(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createSpedizioneErrorTest() {
        log.debug("create spedizione error");

        SpedizioniReq req = new SpedizioniReq();
        req.setOrdineId(9999);
        req.setCorriereId(9999); 

        ResponseEntity<Resp> resp = spedC.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg()).isEqualTo("Ordine o Corriere non trovato");
    }

    @Test
    @Order(3)
    public void findByIdTest() {

    	Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
    	Corrieri corriere = TestDataFactory.creaCorriereValido(corR);

        SpedizioniReq req = new SpedizioniReq();
        req.setOrdineId(ordine.getId());
        req.setCorriereId(corriere.getId());
        req.setTrackingNumber("TRACK456");
        req.setCosto(BigDecimal.valueOf(15));

        spedC.create(req);

        ResponseEntity<?> resp = spedC.findById(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        SpedizioniDTO sped = (SpedizioniDTO) resp.getBody();
        Assertions.assertThat(sped.getTrackingNumber()).isEqualTo("TRACK456");
    }

    @Test
    @Order(4)
    public void findByIdErrorTest() {
        ResponseEntity<?> resp = spedC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Spedizione non trovata");
    }
    
    @Test
    @Order(5)
    public void updateSpedizioneTest() {
        log.debug("update spedizione test");

        Spedizioni sped = speR.findAll().get(0);

        SpedizioniReq req = new SpedizioniReq();
        req.setId(sped.getId());
        req.setTrackingNumber("TRACK789");
        req.setCosto(BigDecimal.valueOf(20));

        ResponseEntity<Resp> resp = spedC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));

        SpedizioniDTO updated = (SpedizioniDTO) spedC.findById(sped.getId()).getBody();
        Assertions.assertThat(updated.getTrackingNumber()).isEqualTo("TRACK789");
        Assertions.assertThat(updated.getCosto()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }
    
    @Test
    @Order(6)
    public void deleteSpedizioneTest() {
        log.debug("delete spedizione test");

        Spedizioni sped = speR.findAll().get(0);
        ResponseEntity<Resp> resp = spedC.delete(sped.getId());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        
        Resp r = resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));

        ResponseEntity<?> respNotFound = spedC.findById(sped.getId());
        assertEquals(HttpStatus.BAD_REQUEST, respNotFound.getStatusCode());

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }
    
    @Test
    @Order(7)
    public void listSpedizioniTest() {
        log.debug("list spedizioni test");

        List<SpedizioniDTO> list = (List<SpedizioniDTO>) spedC.list().getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
        list.forEach(s -> log.debug(s.toString()));
    }
}