package com.betacom.controllers.commerce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.RegisterReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.UtentiReqResp;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.IUtentiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("rest/utente")
public class UtentiController {

	private final IUtentiServices utS;
	private final IMessaggiServices msgS;
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true)  UtentiReq req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		 
		try {
			utS.create(req);
			r.setMsg(msgS.get("rest_created"));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Resp> update(@RequestBody(required = true)  UtentiReq req){
		log.debug("REQUEST BODY: {}", req);
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			utS.update(req);
			r.setMsg(msgS.get("rest_updated"));
		} catch (Exception e) {
			log.debug("Error:" + e.getMessage());
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@PutMapping("/Allupdate")
	public ResponseEntity<Resp> Allupdate(@RequestBody(required = true)  UtentiReqResp req){
		log.debug("REQUEST BODY: {}", req);
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			utS.Allupdate(req);
			r.setMsg(msgS.get("rest_updated"));
		} catch (Exception e) {
			log.debug("Error:" + e.getMessage());
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Resp> delete(@PathVariable(required = true)  String username){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			utS.delete(username);
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
			r= utS.list();
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@GetMapping("/findByUserName")
	public ResponseEntity<Object> findByUserName(@RequestParam (required = true)  String userName){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= utS.getByUserName(userName);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("/findAllByUserName")
	public ResponseEntity<Object> findAllByUserName(@RequestParam (required = true)  String userName){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= utS.getAllByUser(userName);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody(required = true) LoginReq req){
        Object r = new Object();
        HttpStatus status = HttpStatus.OK;
        try {
        	log.debug(req.getUsername());
            r = utS.login(req);
        } catch (Exception e) {
            r = new Resp();
            ((Resp) r).setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterReq req) {
	    Object r;
	    HttpStatus status = HttpStatus.OK;
	    try {
	        log.debug("Register.... {}", req);
	        r = utS.register(req.getUtente(), req.getCliente());
	    } catch (Exception e) {
	        r = new Resp();
	        ((Resp) r).setMsg(e.getMessage());
	        status = HttpStatus.BAD_REQUEST;
	    }
	    return ResponseEntity.status(status).body(r);
	}

}