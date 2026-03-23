package com.betacom.services.implementations.commerce.checkout;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
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
	
	private final IOrdiniRepository ordR;
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void create(SpedizioniReq req) throws Exception {
		log.debug("create {}", req);
		
		if (req.getIndirizzo() == null)
			throw new ZooException("Indirizzo non trovato.");
		if (req.getCorriere() == null)
			throw new ZooException("Corriere non trovato.");
		if (req.getTrackingNumber() == null)
			throw new ZooException("Numero di tracciamento non trovato.");
		if (req.getCosto() == null)
			throw new ZooException("Costo non trovato.");
		if (req.getOrdineId() == null)
			throw new ZooException("Ordine collegato non trovato.");
		
		Ordini ordine = ordR.findById(req.getOrdineId())
	            .orElseThrow(() -> new ZooException("Ordine non trovato nel DB"));
		
		
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(SpedizioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
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
