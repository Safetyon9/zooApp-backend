package com.betacom.services.implementations;

import org.springframework.stereotype.Service;

import com.betacom.services.interfaces.IMessaggiServices;
	
	@Service
	public class MessaggiImpl implements IMessaggiServices {

	    @Override
	    public String get(String code) {
	        
	        return "Messaggio per codice: " + code;
	    }
	}

