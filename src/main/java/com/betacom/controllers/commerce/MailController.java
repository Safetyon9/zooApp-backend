package com.betacom.controllers.commerce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.input.MailReq;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.IMailServices;
import com.betacom.jpa.services.interfaces.IMessageServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping ("rest/mail")
public class MailController {

	private final IMailServices mailS;
	private final IMessageServices   msgS;
	
	@PostMapping("/send")
	public ResponseEntity<Resp> send(@RequestBody (required = true) MailReq req) {
		Resp r = new Resp(); 
		HttpStatus status = HttpStatus.OK;
		try {
			mailS.sendMail(req);
			r.setMsg(msgS.get("rest_created"));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

}
