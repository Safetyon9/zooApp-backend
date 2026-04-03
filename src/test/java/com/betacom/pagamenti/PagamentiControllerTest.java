package com.betacom.pagamenti;

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

import com.betacom.controllers.commerce.checkout.PagamentiController;
import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.ICouponsRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IPagamentiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentiControllerTest {

	@Autowired
    private PagamentiController pagC;
	
	@Autowired
	private IPagamentiRepository pagR;
	
	@Autowired
    private IOrdiniRepository ordR;
	
	@Autowired
    private IMetodiPagamentiRepository mpR;
	
	@Autowired
    private IClientiRepository clR;
	
	@Autowired
    private IUtentiRepository utR;
	
	@Autowired
	private ICouponsRepository couR;
	
	@Autowired
	private IMessaggiServices msgS;

    @Test
    @Order(1)
    public void createPagamentoTest() {

        log.debug("create pagamento OK");

        Ordini ordine = TestDataFactory.creaOrdineValido(ordR, clR, utR);
        MetodiPagamento metodo = TestDataFactory.creaMetodoPagamentoValido(mpR);
        Coupons coupon = TestDataFactory.creaCouponValido(couR);

        PagamentiReq req = new PagamentiReq();
        req.setOrdineId(ordine.getId());
        req.setMetodoPagamentoId(metodo.getId());
        req.setImporto(BigDecimal.valueOf(100));
        req.setStato("PAGATO");
        req.setCouponId(coupon.getId());

        ResponseEntity<Resp> resp = pagC.create(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
    }

    @Test
    @Order(2)
    public void createPagamentoErrorTest() {

        log.debug("create pagamento error");

        PagamentiReq req = new PagamentiReq();

        ResponseEntity<Resp> resp = pagC.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Ordine collegato non trovato.");
    }
    
    @Test
    @Order(3)
    public void findByIdPagamentoTest() {

    	Pagamenti pagamento = TestDataFactory.creaPagamentoValido(pagR ,ordR, clR, utR, mpR);

        ResponseEntity<?> resp = pagC.findById(pagamento.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        PagamentiDTO pag = (PagamentiDTO) resp.getBody();
        Assertions.assertThat(pag.getImporto()).isEqualByComparingTo(BigDecimal.valueOf(150));
    }

    @Test
    @Order(4)
    public void findByIdPagamentoErrorTest() {
        ResponseEntity<?> resp = pagC.findById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Pagamento non trovato in DB");
    }

    @Test
    @Order(5)
    public void updatePagamentoTest() {

        log.debug("update pagamento");

        Pagamenti pagamento = TestDataFactory.creaPagamentoValido(pagR ,ordR, clR, utR, mpR);

        PagamentiReq updateReq = new PagamentiReq();
        updateReq.setId(pagamento.getId());
        updateReq.setImporto(BigDecimal.valueOf(250));

        ResponseEntity<Resp> resp = pagC.update(updateReq);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        
        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
    }

    @Test
    @Order(6)
    public void deletePagamentoTest() {

        log.debug("delete pagamento");

        Pagamenti pagamento = TestDataFactory.creaPagamentoValido(pagR ,ordR, clR, utR, mpR);

        ResponseEntity<Resp> resp = pagC.delete(pagamento.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
    }
    
    @Test
    @Order(7)
    public void listPagamentiTest() {
        log.debug("list pagamenti");

        ResponseEntity<?> resp = pagC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<PagamentiDTO> list = (List<PagamentiDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

}