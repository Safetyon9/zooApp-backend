package com.betacom.carrelli;

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
import org.springframework.test.context.ActiveProfiles;

import com.betacom.controllers.commerce.CarrelliController;
import com.betacom.dto.inputs.commerce.CarrelliReq;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelliControllerTest {

    @Autowired
    private CarrelliController carrelliC;

    @Autowired
    private ICarrelliRepository carrelliR;

    @Autowired
    private IClientiRepository clR;

    @Autowired
    private IUtentiRepository utR;
    
    @Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createCarrelloTest() {
        log.debug("create carrello");

        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);

        CarrelliReq req = new CarrelliReq();
        req.setClienteId(cliente.getId());

        ResponseEntity<Resp> resp = carrelliC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createCarrelloErrorTest() {
        log.debug("create carrello error");

        CarrelliReq req = new CarrelliReq();
        req.setClienteId(9999);

        ResponseEntity<Resp> resp = carrelliC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(3)
    public void getByIdCarrelloTest() {
        log.debug("get carrello by id");
        
        Carrelli carrello = TestDataFactory.creaCarrelloValido(carrelliR, clR, utR);

        ResponseEntity<?> resp = carrelliC.findById(carrello.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        CarrelliDTO cart = (CarrelliDTO) resp.getBody();
    }

    @Test
    @Order(4)
    public void getByIdErrorTest() {
        ResponseEntity<?> resp = carrelliC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(5)
    public void deleteCarrelloTest() {
        log.debug("delete carrello");

        Carrelli carrello = TestDataFactory.creaCarrelloValido(carrelliR, clR, utR);

        ResponseEntity<Resp> resp = carrelliC.delete(carrello.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg()).isEqualTo("Messaggio per codice: rest_deleted");
    }

    @Test
    @Order(6)
    public void listCarrelliTest() {
        log.debug("list carrelli");

        ResponseEntity<?> resp = carrelliC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<CarrelliDTO> list = (List<CarrelliDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);

        list.forEach(c -> log.debug(c.toString()));
    }
}