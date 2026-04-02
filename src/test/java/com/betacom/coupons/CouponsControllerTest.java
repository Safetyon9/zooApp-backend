package com.betacom.coupons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.checkout.CouponsController;
import com.betacom.dto.inputs.commerce.checkout.CouponsReq;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;
import com.betacom.response.Resp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CouponsControllerTest {

    private final CouponsController couC;

    @Test
    @Order(1)
    public void createCouponsTest() {

        log.debug("create coupons OK");

        CouponsReq req = new CouponsReq();
        req.setCodice("AF3251DE");
        req.setTipo("FISSO");
        req.setValore(BigDecimal.valueOf(19.99));
        req.setAttivo(true);
        req.setDataInizio("2026-04-01");
        req.setDataFine("2026-04-30");

        ResponseEntity<Resp> resp = couC.create(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void createCouponsErrorTest() {
        log.debug("create spedizione KO");

        CouponsReq req = new CouponsReq();
        req.setId(9999);
        ResponseEntity<Resp> resp = couC.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Coupons non trovato nel DB");
    }

    @Test
    @Order(3)
    public void getByIdTest() {

    	CouponsReq req = new CouponsReq();
    	req.setCodice("AF3121DU");
        req.setTipo("PERCENTUALE");
        req.setValore(BigDecimal.valueOf(19.99));
        req.setAttivo(true);
        req.setDataInizio("2026-03-01");
        req.setDataFine("2026-03-15");

        couC.create(req);

        ResponseEntity<?> resp = couC.getById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        CouponsDTO coupons = (CouponsDTO) resp.getBody();
        Assertions.assertThat(coupons.getCodice()).isEqualTo("AF3121DU");
    }

    @Test
    @Order(4)
    public void getByIdErrorTest() {
        ResponseEntity<?> resp = couC.getById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Coupons non trovato nel DB");
    }
    
    @Test
    @Order(5)
	public void update() {
		log.debug("*** Update ***");
		
		CouponsReq req = new CouponsReq();
		req.setId(3);
	    req.setValore(BigDecimal.valueOf(9.99));
		
		ResponseEntity<Resp> resp = couC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
	
	}
    
    @Test
    @Order(6)
    public void delete() {
	    log.debug("*** delete ***");
		
		ResponseEntity<Resp> resp = couC.delete(3);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");	
    }
}