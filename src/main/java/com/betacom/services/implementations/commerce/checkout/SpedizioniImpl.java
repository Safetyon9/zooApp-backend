package com.betacom.services.implementations.commerce.checkout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.enums.StatoSpedizione;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.ISpedizioniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.ISpedizioniServices;
import com.betacom.utilities.Mapper;

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
	public void create(SpedizioniReq req) throws ZooException {
		log.debug("create {}", req);
		
		if (req.getIndirizzo() == null)
	        throw new ZooException("Indirizzo non trovato.");
	    if (req.getCorriere() == null)
	        throw new ZooException("Corriere non trovato.");
	    if (req.getCosto() == null)
	        throw new ZooException("Costo non trovato.");
	    if (req.getOrdineId() == null)
	        throw new ZooException("Ordine collegato non trovato.");

	    Ordini ordine = ordR.findById(req.getOrdineId())
	            .orElseThrow(() -> new ZooException("Ordine non trovato nel DB"));

	    Spedizioni sped = new Spedizioni();
	    sped.setIndirizzo(req.getIndirizzo());
	    sped.setCorriere(req.getCorriere());
	    sped.setTrackingNumber(req.getTrackingNumber());
	    sped.setCosto(req.getCosto());
	    sped.setOrdine(ordine);

	    speR.save(sped);
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(SpedizioniReq req) throws ZooException {
		log.debug("update {}", req);

	    Spedizioni sped = speR.findById(req.getId())
	            .orElseThrow(() -> new ZooException("Spedizione non trovata"));

	    if (req.getTrackingNumber() != null)
	        sped.setTrackingNumber(req.getTrackingNumber());

	    if (req.getStato() != null)
	        sped.setStato(StatoSpedizione.valueOf(req.getStato().toUpperCase()));

	    if (req.getDataAggiornamento() != null)
	        sped.setDataAggiornamento(req.getDataAggiornamento());

	    speR.save(sped);
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void delete(Integer id) throws ZooException {
		log.debug("delete {}", id);

	    Spedizioni sped = speR.findById(id)
	            .orElseThrow(() -> new ZooException("Spedizione non trovata"));

	    speR.delete(sped);
	}
	
	@Override
	public List<SpedizioniDTO> list() {
		log.debug("list");

	    List<Spedizioni> lS = speR.findAll();
	    
	    return lS.stream()
	    		.map(s -> Mapper.buildSpedizioniDTO(s))
	    		.collect(Collectors.toList());
	}
	
	@Override
	public SpedizioniDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);

	    Spedizioni sped = speR.findById(id)
	            .orElseThrow(() -> new ZooException("Spedizione non trovata"));

	    return Mapper.buildSpedizioniDTO(sped);
	}
}
