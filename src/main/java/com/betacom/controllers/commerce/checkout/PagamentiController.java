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

import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IPagamentiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/pagamenti")
public class PagamentiController {
	
	private final IPagamentiServices pagaS;
	private final IMessaggiServices msgS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list(
			@RequestParam (required = false) int x
			){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= pagaS.list();
			log.debug("response ok");
		} catch (Exception e) {
			log.debug("error:" + e.getMessage());
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
		
	}
	@GetMapping("/findByAttivita")
	public ResponseEntity<Object> findByAttivita (@RequestParam (required = true)  String attivita){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= pagaS.findByAttivita(attivita);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
		
	}

	@GetMapping("/findById")
	public ResponseEntity<Object> findById (@RequestParam (required = true)  Integer id){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= pagaS.findById(id);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST; 
		}
		return ResponseEntity.status(status).body(r);
		
	}

	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true)  SocioReq req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			pagaS.create(req);
			r.setMsg(msgS.get("rest_created"));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Resp> update(@RequestBody(required = true)  SocioReq req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			pagaS.update(req);
			r.setMsg(msgS.get("rest_updated"));
		} catch (Exception e) {
			log.debug("Error:" + e.getMessage());
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Resp> delete(@PathVariable(required = true)  Integer id){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			pagaS.delete(id);
			r.setMsg(msgS.get("rest_deleted"));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}

	
}