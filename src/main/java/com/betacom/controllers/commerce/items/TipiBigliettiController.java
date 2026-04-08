package com.betacom.controllers.commerce.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.betacom.dto.inputs.commerce.items.TipiBigliettiReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.ITipiBigliettiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/tipibiglietti")
public class TipiBigliettiController {

    private final ITipiBigliettiServices tipiS;
    private final IMessaggiServices msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody TipiBigliettiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            tipiS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            log.debug("Error create: " + e.getMessage());
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody TipiBigliettiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            tipiS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            log.debug("Error update: " + e.getMessage());
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
            tipiS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            log.debug("Error delete: " + e.getMessage());
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
            r = tipiS.findAll();
            log.debug("list ok");
        } catch (Exception e) {
            log.debug("Error list: " + e.getMessage());
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam Integer id) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = tipiS.getById(id);
        } catch (Exception e) {
            log.debug("Error findById: " + e.getMessage());
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}