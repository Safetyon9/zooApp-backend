package com.betacom.services.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.MailReq;
import com.betacom.exceptions.ZooException;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailImpl implements IMailServices{

	
	@Value("${mail.sender}")
	private String from;
	
	
	
	private final JavaMailSender mailSender;
	private final IMessaggiServices msgS;
	
	
	@Override
	public void sendMail(MailReq req) throws Exception {
	    log.debug("sendMail []", req);

	    if (req.getTo() == null || req.getOggetto() == null || req.getBody() == null) {
	        throw new ZooException(msgS.get("mail_error"));
	    }

	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

	    helper.setTo(req.getTo());
	    helper.setFrom(from);
	    helper.setSubject(req.getOggetto());
	    helper.setText(req.getBody(), true);

	    if (req.getAttachment() != null && req.getAttachment().length > 0) {
	        helper.addInline(
	            "email-bg",
	            new ByteArrayResource(req.getAttachment()),
	            "image/jpeg"
	        );
	    }

	    if (req.getPdfRicevuta() != null && req.getPdfRicevuta().length > 0) {
	      String nomeFile = (req.getPdfFileName() != null && !req.getPdfFileName().isBlank())
	              ? req.getPdfFileName()
	              : "ricevuta.pdf";

	      helper.addAttachment(
	          nomeFile,
	          new ByteArrayResource(req.getPdfRicevuta())
	      );
	    }

	    mailSender.send(mimeMessage);
	    log.debug("dopo send");
	}

}
