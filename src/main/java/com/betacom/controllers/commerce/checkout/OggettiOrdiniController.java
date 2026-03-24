package com.betacom.controllers.commerce.checkout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOggettiOrdiniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rest/oggettoordine")
public class OggettiOrdiniController {

    private final IOggettiOrdiniServices ogS;
    private final IMessaggiServices msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) OggettiOrdiniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ogS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) OggettiOrdiniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ogS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            log.debug("Error:" + e.getMessage());
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete/{idOggetto}")
    public ResponseEntity<Resp> delete(@PathVariable(required = true) Integer idOggetto) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ogS.delete(idOggetto);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
            r = ogS.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam(required = true) Integer idOggetto) {
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
            r = ogS.getById(idOggetto);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}