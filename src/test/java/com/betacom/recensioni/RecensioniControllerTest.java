package com.betacom.recensioni;

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

import com.betacom.controllers.commerce.RecensioniController;
import com.betacom.dto.inputs.commerce.RecensioniReq;
import com.betacom.dto.outputs.commerce.RecensioniDTO;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Recensioni;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.IRecensioniRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecensioniControllerTest {

    @Autowired
    private RecensioniController recensioniC;
    
    @Autowired
    private IRecensioniRepository recR;

    @Autowired
    private IClientiRepository clR;

    @Autowired
    private IUtentiRepository utR;

    @Autowired
    private ITipiBigliettiRepository tipiR;
    
    @Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createRecensioneTest() {
        log.debug("create recensione");

        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);

        RecensioniReq req = new RecensioniReq();
        req.setClienteId(cliente.getId());
        req.setItemId(null);
        req.setVoto(5);
        req.setTitolo("Bellissimo");
        req.setTesto("Molto divertente");
        req.setGeneraleZoo(true);

        ResponseEntity<Resp> resp = recensioniC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createRecensioneErrorTest() {
        log.debug("create recensione error");

        RecensioniReq req = new RecensioniReq();
        req.setClienteId(9999);
        req.setItemId(9999);
        req.setVoto(4);
        req.setTitolo("Errore");
        req.setTesto("Cliente o Item non esiste");
        req.setGeneraleZoo(false);

        ResponseEntity<Resp> resp = recensioniC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Cliente o Item non trovato");
    }

    @Test
    @Order(3)
    public void findByIdRecensioneTest() {
        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
        TipiBiglietti item = TestDataFactory.creaTipoBigliettoValido(tipiR);

        RecensioniReq req = new RecensioniReq();
        req.setClienteId(cliente.getId());
        req.setItemId(item.getId());
        req.setVoto(4);
        req.setTitolo("Molto bello");
        req.setTesto("Divertente");
        req.setGeneraleZoo(true);

        recensioniC.create(req);

        ResponseEntity<?> resp = recensioniC.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        RecensioniDTO dto = (RecensioniDTO) resp.getBody();
        Assertions.assertThat(dto.getVoto()).isEqualTo(4);
        Assertions.assertThat(dto.getTitolo()).isEqualTo("Molto bello");
    }

    @Test
    @Order(4)
    public void findByIdErrorTest() {
        ResponseEntity<?> resp = recensioniC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Recensione non trovata");
    }

    @Test
    @Order(5)
    public void updateRecensioneTest() {
        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
        TipiBiglietti item = TestDataFactory.creaTipoBigliettoValido(tipiR);

        RecensioniReq req = new RecensioniReq();
        req.setClienteId(cliente.getId());
        req.setItemId(item.getId());
        req.setVoto(3);
        req.setTitolo("Bello");
        req.setTesto("Interessante");
        req.setGeneraleZoo(false);

        recensioniC.create(req);

        RecensioniReq updateReq = new RecensioniReq();
        updateReq.setId(1);
        updateReq.setVoto(5);
        updateReq.setTitolo("Aggiornato");
        updateReq.setTesto("Super divertente");

        ResponseEntity<Resp> resp = recensioniC.update(updateReq);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(6)
    public void deleteRecensioneTest() {
        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
        TipiBiglietti item = TestDataFactory.creaTipoBigliettoValido(tipiR);

        RecensioniReq req = new RecensioniReq();
        req.setClienteId(cliente.getId());
        req.setItemId(item.getId());
        req.setVoto(2);
        req.setTitolo("Da cancellare");
        req.setTesto("Test delete");
        req.setGeneraleZoo(false);

        recensioniC.create(req);

        ResponseEntity<Resp> resp = recensioniC.delete(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = (Resp) resp.getBody();

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }
    
    @Test
    @Order(7)
    public void listPagamentiTest() {
        log.debug("list pagamenti");

        ResponseEntity<?> resp = recensioniC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<RecensioniDTO> list = (List<RecensioniDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }
}