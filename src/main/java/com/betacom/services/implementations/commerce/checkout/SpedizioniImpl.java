package com.betacom.services.implementations.commerce.checkout;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.persistence.repository.commerce.checkout.ISpedizioniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.ISpedizioniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpedizioniImpl implements ISpedizioniServices{
	
	private final ISpedizioniRepository speR;
	private final IMessaggiServices msgS;
	
	@Override
	public void create(SpedizioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(SpedizioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<SpedizioniDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpedizioniDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
