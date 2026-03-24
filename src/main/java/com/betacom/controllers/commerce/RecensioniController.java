package com.betacom.controllers.commerce;

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

import com.betacom.dto.inputs.commerce.RecensioniReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.IRecensioniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/recensioni")
public class RecensioniController {

    private final IRecensioniServices recS;
    private final IMessaggiServices msgS;

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
            r = recS.list();
            log.debug("response ok");
        } catch (Exception e) {
            log.debug("error:" + e.getMessage());
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam(required = true) Integer id) {
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
            r = recS.getById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) RecensioniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            recS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) RecensioniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            recS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            log.debug("Error:" + e.getMessage());
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Resp> delete(@PathVariable(required = true) Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            recS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
