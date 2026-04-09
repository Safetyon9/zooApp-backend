package com.betacom.controllers.commerce.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IBigliettiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/biglietti")
public class BigliettiController {

    private final IMessaggiServices msgS;
    private final IBigliettiServices bigliettiS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody BigliettiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            bigliettiS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody BigliettiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            bigliettiS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Resp> delete(@PathVariable Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            bigliettiS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = bigliettiS.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PostMapping("/search")
    public ResponseEntity<Object> search(@RequestBody BigliettiReq filtro) {
        HttpStatus status = HttpStatus.OK;
        Object r;
        try {
            r = bigliettiS.search(filtro);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        HttpStatus status = HttpStatus.OK;
        Object r;
        try {
            r = bigliettiS.getById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }


}
