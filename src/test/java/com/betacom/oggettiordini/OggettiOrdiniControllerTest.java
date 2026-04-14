package com.betacom.oggettiordini;

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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.betacom.controllers.commerce.checkout.OggettiOrdiniController;
import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOggettiOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;
import com.betacom.utilities.Utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OggettiOrdiniControllerTest {
	
	@MockitoBean
	private IMailServices mailServices;
	
    @Autowired
    private OggettiOrdiniController ooC;

    @Autowired
    private IOrdiniRepository ordiniR;

    @Autowired
    private IClientiRepository clR;

    @Autowired
    private IUtentiRepository utR;
    
    @Autowired
    private IMessaggiServices msgS;

    @Autowired
    private IOggettiOrdiniRepository ooR;
    
    @Autowired
    private IBigliettiRepository bigliettoR;
    
    @Autowired
    private ITipiBigliettiRepository tipiR;
    
    @Test
    @Order(1)
    public void createOggettoOrdineTest() {
        log.debug("Create oggetto ordine");

        Ordini ordine = TestDataFactory.creaOrdineValido(ordiniR, clR, utR);
        Biglietti item = TestDataFactory.creaBigliettoValido(bigliettoR, tipiR);
        

        OggettiOrdiniReq req = new OggettiOrdiniReq();
        req.setOrdineId(ordine.getId());
        req.setItemId(item.getId());
        req.setQuantita(2);
        req.setPrezzoUnitario(item.getPrezzo());
        req.setPrezzoTotale(Utils.calcolaPrezzoTotale(req.getQuantita(),req.getPrezzoUnitario()));
        

        ResponseEntity<?> resp = ooC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());

		Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));

    }

    @Test
    @Order(2)
    public void createOggettoOrdineErrorTest() {
        log.debug("Create oggetto ordine error");

        OggettiOrdiniReq req = new OggettiOrdiniReq();
        req.setId(9999);
        ResponseEntity<Resp> resp = ooC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(3)
    public void getByIdTest() {
        log.debug("Get oggetto ordine by id");

        OggettiOrdini oo = TestDataFactory.creaOggettiOrdiniValido(ooR, ordiniR, clR, utR, tipiR, bigliettoR);
        
        ResponseEntity<?> resp = ooC.findById(oo.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        OggettiOrdiniDTO dto = (OggettiOrdiniDTO) resp.getBody();
        Assertions.assertThat(dto.getQuantita()).isEqualTo(2);
        Assertions.assertThat(dto.getPrezzoTotale()).isEqualTo(new BigDecimal("31.00"));
    }

    @Test
    @Order(4)
    public void getByIdErrorTest() {
        ResponseEntity<?> resp = ooC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(5)
    public void updateOggettoOrdineTest() {
        log.debug("Update oggetto ordine");
        
        OggettiOrdini oo = TestDataFactory.creaOggettiOrdiniValido(ooR, ordiniR, clR, utR, tipiR, bigliettoR);
        
        OggettiOrdiniReq req = new OggettiOrdiniReq();
        req.setId(oo.getId());
        req.setQuantita(3);
        req.setPrezzoTotale(new BigDecimal("60.00"));

        ResponseEntity<Resp> resp = ooC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();
        
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(6)
    public void deleteOggettoOrdineTest() {
        log.debug("Delete oggetto ordine");

        OggettiOrdini oo = TestDataFactory.creaOggettiOrdiniValido(ooR, ordiniR, clR, utR, tipiR, bigliettoR);
        
        ResponseEntity<Resp> resp = ooC.delete(oo.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();
        
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }

    @Test
    @Order(7)
    public void listOggettiOrdineTest() {
        log.debug("List oggetti ordine");

        ResponseEntity<?> resp = ooC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<OggettiOrdiniDTO> list = (List<OggettiOrdiniDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }
    
    @Test
    @Order(8)
    public void updateOggettoOrdineTestErr() {
        log.debug("Update oggetto ordine ERR");

        OggettiOrdiniReq req = new OggettiOrdiniReq();
        req.setId(99999);
        req.setQuantita(3);
        req.setPrezzoTotale(new BigDecimal("60.00"));

        ResponseEntity<Resp> resp = ooC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());

    }
    
    
    @Test
    @Order(9)
    public void deleteOggettoOrdineTestErr() {
        log.debug("Update oggetto ordine ERR");

        ResponseEntity<Resp> resp = ooC.delete(9999);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());

    }
}