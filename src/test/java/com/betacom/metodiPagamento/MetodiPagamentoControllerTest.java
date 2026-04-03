package com.betacom.metodiPagamento;

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

import com.betacom.controllers.commerce.checkout.MetodiPagamentoController;
import com.betacom.dto.inputs.commerce.checkout.MetodiPagamentoReq;
import com.betacom.dto.outputs.commerce.checkout.MetodiPagamentoDTO;
import com.betacom.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MetodiPagamentoControllerTest {

    @Autowired
    private MetodiPagamentoController mpC;
    
    private static Integer createdId;
    
    @Test
    @Order(1)
    public void createMetodiPagamento() {
        log.debug("Create metodo pagamento");

        MetodiPagamentoReq req = new MetodiPagamentoReq();
        req.setNome("Carta di Credito");
        req.setProvider("Visa");

        ResponseEntity<Resp> resp = mpC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_created");
        
        List<MetodiPagamentoDTO> list = (List<MetodiPagamentoDTO>) mpC.list().getBody();
        createdId = list.get(list.size() - 1).getId();
    }

    @Test
    @Order(2)
    public void createMetodiPagamentoError() {
        log.debug("Create metodo pagamento error");

        MetodiPagamentoReq req = new MetodiPagamentoReq();

        ResponseEntity<Resp> resp = mpC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(3)
    public void getById() {
        log.debug("Get metodo pagamento by id");

        ResponseEntity<?> resp = mpC.getById(createdId);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        MetodiPagamentoDTO dto = (MetodiPagamentoDTO) resp.getBody();
        Assertions.assertThat(dto.getNome()).isEqualTo("Carta di Credito");
    }

    @Test
    @Order(4)
    public void getByIdError() {
        log.debug("Get metodo pagamento by id error");

        ResponseEntity<?> resp = mpC.getById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(5)
    public void updateMetodiPagamento() {
        log.debug("Update metodo pagamento");

        MetodiPagamentoReq req = new MetodiPagamentoReq();
        req.setId(1);
        req.setNome("Carta di Debito");
        req.setProvider("Mastercard");

        ResponseEntity<Resp> resp = mpC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_updated");
    }

    @Test
    @Order(6)
    public void updateMetodiPagamentoError() {
        log.debug("Update metodo pagamento error");

        MetodiPagamentoReq req = new MetodiPagamentoReq();
        req.setId(9999);

        ResponseEntity<Resp> resp = mpC.update(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(7)
    public void deleteMetodiPagamento() {
        log.debug("Delete metodo pagamento");

        ResponseEntity<Resp> resp = mpC.delete(createdId);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_deleted");
    }

    @Test
    @Order(8)
    public void deleteMetodiPagamentoError() {
        log.debug("Delete metodo pagamento error");

        ResponseEntity<Resp> resp = mpC.delete(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(9)
    public void listMetodiPagamento() {
        log.debug("List metodi pagamento");

        ResponseEntity<?> resp = mpC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<MetodiPagamentoDTO> list = (List<MetodiPagamentoDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);

        list.forEach(mp -> log.debug(mp.toString()));
    }
}