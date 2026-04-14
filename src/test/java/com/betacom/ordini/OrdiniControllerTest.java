package com.betacom.ordini;

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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.betacom.controllers.commerce.checkout.OrdiniController;
import com.betacom.dto.inputs.OrdiniPagReq;
import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IPagamentiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdiniControllerTest {
	
	@MockitoBean
	private IMailServices mailServices;

    @Autowired
    private OrdiniController ordC;

    @Autowired
    private IClientiRepository clR;

    @Autowired
    private IUtentiRepository utR;
    
    @Autowired
	private IMessaggiServices msgS;
    
    @Autowired
    private IOrdiniRepository ordR;
    
    @Autowired
    private IPagamentiRepository pagR;
    
    @Autowired
    private IMetodiPagamentiRepository mpR;

    @Test
    @Order(1)
    public void createOrdineTest() {
        log.debug("create ordine");
        
        Ordini o = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);

        
        OrdiniReq oReq = new OrdiniReq();
        oReq.setClienteId(cliente.getId());
        oReq.setIndirizzo(cliente.getIndirizzo());
        
        Pagamenti p = TestDataFactory.creaPagamentoValido(pagR, ordR, clR, utR, mpR);
        PagamentiReq pagReq = new PagamentiReq();
        pagReq.setCouponId(p.getCoupon().getId());
        pagReq.setImporto(p.getImporto());
        pagReq.setMetodoPagamentoId(p.getMetodoPagamento().getId());
        pagReq.setOrdineId(p.getOrdine().getId());
        
        
        OrdiniPagReq req = new OrdiniPagReq();
        req.setOrdini(oReq);
        req.setPagamenti(pagReq);

        ResponseEntity<Resp> resp = ordC.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        
        Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

//    @Test
//    @Order(2)
//    public void createOrdineErrorTest() {
//        log.debug("create ordine error");
//
//        Ordini o = TestDataFactory.creaOrdineValido(ordR, clR, utR);
//        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
//
//        
//        OrdiniReq oReq = new OrdiniReq();
//        oReq.setClienteId(cliente.getId());
//        oReq.setIndirizzo(cliente.getIndirizzo());
//        
//        Pagamenti p = TestDataFactory.creaPagamentoValido(pagR, ordR, clR, utR, mpR);
//        PagamentiReq pagReq = new PagamentiReq();
//        pagReq.setCouponId(p.getCoupon().getId());
//        pagReq.setImporto(p.getImporto());
//        pagReq.setMetodoPagamentoId(p.getMetodoPagamento().getId());
//        pagReq.setOrdineId(p.getOrdine().getId());
//        
//        
//        OrdiniPagReq req = new OrdiniPagReq();
//        req.setOrdini(oReq);
//        req.setPagamenti(pagReq);
//
//        ResponseEntity<Resp> resp = ordC.create(req);
//        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
//        Assertions.assertThat(resp.getBody()).isNotNull();
//    }
//
//    @Test
//    @Order(3)
//    public void getByIdOrdineTest() {
//        log.debug("get ordine by id");
//
//        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
//
//        OrdiniReq req = new OrdiniReq();
//        req.setClienteId(cliente.getId());
//        req.setIndirizzo(cliente.getIndirizzo());
//        ordC.create(req);
//
//        ResponseEntity<?> resp = ordC.findById(1);
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//
//        OrdiniDTO dto = (OrdiniDTO) resp.getBody();
//        Assertions.assertThat(dto.getClienteId()).isEqualTo(cliente.getId());
//        Assertions.assertThat(dto.getIndirizzo()).isEqualTo(cliente.getIndirizzo());
//    }
//
//    @Test
//    @Order(4)
//    public void getByIdErrorTest() {
//        ResponseEntity<?> resp = ordC.findById(9999);
//        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
//    }
//
//    @Test
//    @Order(5)
//    public void updateOrdineTest() {
//        log.debug("update ordine");
//
//        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
//
//        OrdiniReq req = new OrdiniReq();
//        req.setClienteId(cliente.getId());
//        req.setIndirizzo(cliente.getIndirizzo());
//        ordC.create(req);
//
//        OrdiniReq updateReq = new OrdiniReq();
//        updateReq.setId(1);
//        updateReq.setIndirizzo("Via Torino 10");
//
//        ResponseEntity<Resp> resp = ordC.update(updateReq);
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        
//        Resp r = (Resp) resp.getBody();
//
//		Assertions.assertThat(r.getMsg())
//        .isEqualTo(msgS.get("rest_updated"));
//    }
//
//    @Test
//    @Order(6)
//    public void deleteOrdineTest() {
//        log.debug("delete ordine");
//
//        Clienti cliente = TestDataFactory.creaClienteValido(clR, utR);
//
//        OrdiniReq req = new OrdiniReq();
//        req.setClienteId(cliente.getId());
//        req.setIndirizzo(cliente.getIndirizzo());
//        ordC.create(req);
//
//        ResponseEntity<Resp> resp = ordC.delete(1);
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        
//        Resp r = (Resp) resp.getBody();
//
//		Assertions.assertThat(r.getMsg())
//        .isEqualTo(msgS.get("rest_deleted"));
//    }
//
//    @Test
//    @Order(7)
//    public void listOrdiniTest() {
//        log.debug("list ordini");
//
//        ResponseEntity<?> resp = ordC.list();
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//
//        List<OrdiniDTO> list = (List<OrdiniDTO>) resp.getBody();
//        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
//    }
}