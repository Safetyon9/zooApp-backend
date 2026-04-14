package com.betacom.corrieri;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.betacom.controllers.commerce.checkout.CorrieriController;
import com.betacom.dto.inputs.commerce.checkout.CorrieriReq;
import com.betacom.dto.outputs.commerce.checkout.CorrieriDTO;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CorrieriControllerTest {

    @Autowired
    private CorrieriController corrieriC;

    @Autowired
    private ICorrieriRepository corR;

    @Autowired
    private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createCorriere() {
        log.debug("Create corriere");
        CorrieriReq req = new CorrieriReq();
        req.setNome("UPS");

        ResponseEntity<Resp> resp = corrieriC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

        Assertions.assertThat(r.getMsg())
            .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void getById() {
        log.debug("getById test");
        // Uso la factory per essere sicuri di avere un ID 1 o quello creato
        Corrieri c = TestDataFactory.creaCorriereValido(corR);
        
        ResponseEntity<?> resp = corrieriC.getById(c.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        CorrieriDTO dto = (CorrieriDTO) resp.getBody();
        Assertions.assertThat(dto.getNome()).contains("DHL"); // La factory crea DHL+timestamp
    }

    @Test
    @Order(3)
    public void updateCorriere() {
        log.debug("Update corriere");
        Corrieri c = TestDataFactory.creaCorriereValido(corR);
        
        CorrieriReq req = new CorrieriReq();
        req.setId(c.getId());
        req.setNome("DHL Express Updated");

        ResponseEntity<Resp> resp = corrieriC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

        Assertions.assertThat(r.getMsg())
            .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(4)
    public void list() {
        log.debug("Test list corrieri");

        ResponseEntity<?> resp = corrieriC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        List<CorrieriDTO> list = (List<CorrieriDTO>) resp.getBody();

        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Order(5)
    public void deleteCorriere() {
        log.debug("delete corriere");
        Corrieri c = TestDataFactory.creaCorriereValido(corR);

        ResponseEntity<Resp> resp = corrieriC.delete(c.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

        Assertions.assertThat(r.getMsg())
            .isEqualTo(msgS.get("rest_deleted"));
    }
}