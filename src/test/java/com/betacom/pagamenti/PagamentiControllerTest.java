package com.betacom.pagamenti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.checkout.PagamentiController;
import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.response.Resp;
import com.betacom.testutils.TestDataFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentiControllerTest {

    private PagamentiController pagC;
    private IOrdiniRepository ordR;
    private IMetodiPagamentiRepository mpR;
    private IClientiRepository clR;
    private IUtentiRepository utR;

    @Test
    @Order(1)
    public void createPagamentoTest() {

        log.debug("create pagamento OK");

        Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        MetodiPagamento metodo = TestDataFactory.creaMetodoPagamentoValido(mpR);

        PagamentiReq req = new PagamentiReq();
        req.setOrdineId(ordine.getId());
        req.setMetodoPagamentoId(metodo.getId());
        req.setImporto(BigDecimal.valueOf(100));
        req.setStato("PAGATO");

        ResponseEntity<Resp> resp = pagC.create(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void createPagamentoErrorTest() {

        log.debug("create pagamento error");

        PagamentiReq req = new PagamentiReq();
        req.setOrdineId(9999);
        req.setMetodoPagamentoId(9999);
        req.setImporto(BigDecimal.valueOf(50));

        ResponseEntity<Resp> resp = pagC.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Ordine o MetodoPagamento non trovato");
    }

    @Test
    @Order(3)
    public void findByIdPagamentoTest() {

        Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        MetodiPagamento metodo = TestDataFactory.creaMetodoPagamentoValido(mpR);

        PagamentiReq req = new PagamentiReq();
        req.setOrdineId(ordine.getId());
        req.setMetodoPagamentoId(metodo.getId());
        req.setImporto(BigDecimal.valueOf(150));
        req.setStato("IN_ATTESA");

        pagC.create(req);

        ResponseEntity<?> resp = pagC.findById(req.getOrdineId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        PagamentiDTO pag = (PagamentiDTO) resp.getBody();
        Assertions.assertThat(pag.getImporto()).isEqualTo(BigDecimal.valueOf(150));
    }

    @Test
    @Order(4)
    public void findByIdPagamentoErrorTest() {
        ResponseEntity<?> resp = pagC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());

        // cast sicuro se il controller restituisce Resp
        Resp r = (Resp) resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Pagamento non trovato");
    }
}