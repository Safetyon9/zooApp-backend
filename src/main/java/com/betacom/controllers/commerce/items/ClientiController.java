package com.betacom.controllers.commerce.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IClientiServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/clienti")
public class ClientiController {
	
	    private final IMessaggiServices msgS;
	    private final IClientiServices clientiS;

	    @PostMapping("/create")
	    public ResponseEntity<Resp> create(@RequestBody ClientiReq req) {
	        Resp r = new Resp();
	        HttpStatus status = HttpStatus.OK;
	        try {
	            clientiS.create(req);
	            r.setMsg(msgS.get("rest_created"));
	        } catch (Exception e) {
	            r.setMsg(e.getMessage());
	            status = HttpStatus.BAD_REQUEST;
	        }
	        return ResponseEntity.status(status).body(r);
	    }

	    @PutMapping("/update")
	    public ResponseEntity<Resp> update(@RequestBody ClientiReq req) {
	        Resp r = new Resp();
	        HttpStatus status = HttpStatus.OK;
	        try {
	            clientiS.update(req);
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
	            clientiS.delete(id);
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
	            r = clientiS.findAll();
	        } catch (Exception e) {
	            r = e.getMessage();
	            status = HttpStatus.BAD_REQUEST;
	        }
	        return ResponseEntity.status(status).body(r);
	    }

}	   