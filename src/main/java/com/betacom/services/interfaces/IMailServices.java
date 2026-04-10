package com.betacom.services.interfaces;

import com.betacom.dto.inputs.MailReq;

public interface IMailServices {
	void sendMail(MailReq req) throws Exception;
}
