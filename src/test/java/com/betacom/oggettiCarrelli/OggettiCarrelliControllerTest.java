package com.betacom.oggettiCarrelli;

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
import com.betacom.controllers.commerce.OggettiCarrelliController;
import com.betacom.dto.inputs.commerce.OggettiCarrelliReq;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;
import com.betacom.utilities.Utils;

import lombok.extern.slf4j.Slf4j;

@Import(TestMailConfig.class)
@ActiveProfiles("test")
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OggettiCarrelliControllerTest {

    @Autowired
    private OggettiCarrelliController ocC;

    @Autowired
    private ICarrelliRepository carrelliR;

    @Autowired
    private IBigliettiRepository bigliettoR;
    
    @Autowired
    private ITipiBigliettiRepository tipiR;

    @Autowired
    private IClientiRepository clR;

    @Autowired
    private IUtentiRepository utR;
    
    @Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createOggettoCarrelloTest() {
        log.debug("Create oggetto carrello");

        Carrelli carrello = TestDataFactory.creaCarrelloValido(carrelliR, clR, utR);
        Biglietti item = TestDataFactory.creaBigliettoValido(bigliettoR, tipiR);

        OggettiCarrelliReq req = new OggettiCarrelliReq();
        req.setCarrelloId(carrello.getId());
        req.setItemId(item.getId());
        req.setQuantita(2);
        req.setPrezzoUnitario(item.getPrezzo());
        req.setPrezzoTotale(Utils.calcolaPrezzoTotale(req.getQuantita(), req.getPrezzoUnitario()));

        ResponseEntity<Resp> resp = ocC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createOggettoCarrelloErrorTest() {
        log.debug("Create oggetto carrello error");

        OggettiCarrelliReq req = new OggettiCarrelliReq();
        req.setCarrelloId(9999);
        req.setItemId(9999);
        ResponseEntity<Resp> resp = ocC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(3)
    public void getByIdTest() {
        log.debug("Get oggetto carrello by id");

        ResponseEntity<?> resp = ocC.getById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        OggettiCarrelliDTO dto = (OggettiCarrelliDTO) resp.getBody();
        Assertions.assertThat(dto.getQuantita()).isEqualTo(2);
        Assertions.assertThat(dto.getPrezzoTotale()).isEqualTo(new BigDecimal("31.00"));
    }

    @Test
    @Order(4)
    public void getByIdErrorTest() {
        ResponseEntity<?> resp = ocC.getById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(5)
    public void updateOggettoCarrelloTest() {
        log.debug("Update oggetto carrello");

        OggettiCarrelliReq req = new OggettiCarrelliReq();
        req.setId(1);
        req.setQuantita(3);
        req.setPrezzoTotale(new BigDecimal("46.50"));

        ResponseEntity<Resp> resp = ocC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        
        Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(6)
    public void deleteOggettoCarrelloTest() {
        log.debug("Delete oggetto carrello");

        ResponseEntity<Resp> resp = ocC.delete(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        
        Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }

    @Test
    @Order(7)
    public void listOggettiCarrelloTest() {
        log.debug("List oggetti carrello");

        ResponseEntity<?> resp = ocC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<OggettiCarrelliDTO> list = (List<OggettiCarrelliDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }
}