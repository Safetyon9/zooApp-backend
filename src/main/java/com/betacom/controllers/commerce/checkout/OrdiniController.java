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

import com.betacom.dto.inputs.OrdiniPagReq;
import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOggettiOrdiniServices;
import com.betacom.services.interfaces.commerce.checkout.IOrdiniServices;
import com.betacom.services.interfaces.commerce.checkout.IPagamentiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rest/ordine")
public class OrdiniController {

    private final IOrdiniServices ordS;
    private final IPagamentiServices pagS;
    private final IOggettiOrdiniServices ooS;
    private final IMessaggiServices msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) OrdiniPagReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            Integer idOrdine = ordS.create(req.getOrdini());
            for(OggettiOrdiniReq riga: req.getRighe()) {
            	riga.setOrdineId(idOrdine);
            	ooS.create(riga);
            }
            req.getPagamenti().setOrdineId(idOrdine);
            pagS.create(req.getPagamenti());
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) OrdiniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ordS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            log.debug("Error:" + e.getMessage());
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete/{idOrdine}")
    public ResponseEntity<Resp> delete(@PathVariable(required = true) Integer idOrdine) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ordS.delete(idOrdine);
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
            r = ordS.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @GetMapping("/my-list/{clienteId}")
    public ResponseEntity<Object> myList(@PathVariable Integer clienteId) {
        try {
            return ResponseEntity.ok(ordS.listByClienteId(clienteId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam(required = true) Integer idOrdine) {
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
            r = ordS.getById(idOrdine);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}