package com.betacom.coupons;

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

import com.betacom.controllers.commerce.checkout.CouponsController;
import com.betacom.dto.inputs.commerce.checkout.CouponsReq;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;
import com.betacom.response.Resp;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CouponsControllerTest {

    @Autowired
    private CouponsController couC;

    @Test
    @Order(1)
    public void createCoupon() {
        log.debug("Create coupon");

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
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_created");
    }

    @Test
    @Order(2)
    public void createCouponError() {
        log.debug("Create coupon error");

        CouponsReq req = new CouponsReq();
        req.setId(9999);

        ResponseEntity<Resp> resp = couC.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg()).isEqualTo("Coupons non trovato nel DB");
    }

    @Test
    @Order(3)
    public void getById() {
        log.debug("Get coupon by id");

        CouponsReq req = new CouponsReq();
        req.setCodice("AF3121DU");
        req.setTipo("PERCENTUALE");
        req.setValore(BigDecimal.valueOf(19.99));
        req.setAttivo(true);
        req.setDataInizio("2026-03-01");
        req.setDataFine("2026-03-15");

        couC.create(req);

        ResponseEntity<?> resp = couC.getById(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        CouponsDTO dto = (CouponsDTO) resp.getBody();
        Assertions.assertThat(dto.getCodice()).isEqualTo("AF3121DU");
    }

    @Test
    @Order(4)
    public void getByIdError() {
        log.debug("Get coupon by id error");

        ResponseEntity<?> resp = couC.getById(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("Coupons non trovato nel DB");
    }

    @Test
    @Order(5)
    public void updateCoupon() {
        log.debug("Update coupon");

        CouponsReq req = new CouponsReq();
        req.setId(1);
        req.setValore(BigDecimal.valueOf(9.99));

        ResponseEntity<Resp> resp = couC.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_updated");
    }

    @Test
    @Order(6)
    public void updateCouponError() {
        log.debug("Update coupon error");

        CouponsReq req = new CouponsReq();
        req.setId(9999);

        ResponseEntity<Resp> resp = couC.update(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(7)
    public void deleteCoupon() {
        log.debug("Delete coupon");

        ResponseEntity<Resp> resp = couC.delete(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("Messaggio per codice: rest_deleted");
    }

    @Test
    @Order(8)
    public void deleteCouponError() {
        log.debug("Delete coupon error");

        ResponseEntity<Resp> resp = couC.delete(9999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    @Order(9)
    public void listCoupons() {
        log.debug("List coupons");

        ResponseEntity<?> resp = couC.list();
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        List<CouponsDTO> list = (List<CouponsDTO>) resp.getBody();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(0);

        list.forEach(c -> log.debug(c.toString()));
    }
}