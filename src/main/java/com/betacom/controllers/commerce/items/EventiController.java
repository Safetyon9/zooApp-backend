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

import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IEventiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/eventi")
public class EventiController {

		
		private final IMessaggiServices    msgS;
		private final IEventiServices    evE;
		
		@PostMapping("/create")
		public ResponseEntity<Resp> create(@RequestBody(required = true)  EventiReq req){
			log.debug("create controller {}", req);
			Resp r = new Resp();
			HttpStatus status = HttpStatus.OK;
			try {
				evE.create(req);
				r.setMsg(msgS.get("rest_created"));
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				status = HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(status).body(r);		
		}
		
		@PutMapping("/update")
		public ResponseEntity<Resp> update(@RequestBody(required = true)  EventiReq req){
			Resp r = new Resp();
			HttpStatus status = HttpStatus.OK;
			try {
				evE.update(req);
				r.setMsg(msgS.get("rest_updated"));
			} catch (Exception e) {
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
				evE.delete(id);
				r.setMsg(msgS.get("rest_deleted"));
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				status = HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(status).body(r);		
		}

		
		@GetMapping("/list")
		public ResponseEntity<Object> list(){
			Object r = new Object();
			HttpStatus status = HttpStatus.OK;
			try {
				r= evE.findAll();
			} catch (Exception e) {
				r=e.getMessage();
				status = HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(status).body(r);
			
		}
	}


