package com.betacom.services.implementations.commerce.checkout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;
import com.betacom.enums.StatoSpedizione;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Spedizioni;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
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
	private final ICorrieriRepository corR;
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void create(SpedizioniReq req) throws ZooException {
		log.debug("create {}", req);
		
	    Ordini ordine = ordR.findById(req.getOrdineId())
	            .orElseThrow(() -> new ZooException("Ordine o Corriere non trovato"));
	    
	    Corrieri corriere = corR.findById(req.getCorriereId())
	            .orElseThrow(() -> new ZooException("Ordine o Corriere non trovato"));
		
		if (ordine.getIndirizzo() == null)
	        throw new ZooException("Indirizzo non trovato.");
	    if (req.getCosto() == null)
	        throw new ZooException("Costo non trovato.");
	    if (req.getOrdineId() == null)
	        throw new ZooException("Ordine collegato non trovato.");

	    Spedizioni sped = new Spedizioni();
	    sped.setCorriere(corriere);
	    sped.setTrackingNumber(req.getTrackingNumber());
	    sped.setCosto(req.getCosto());
	    sped.setOrdine(ordine);
	    sped.setIndirizzo(ordine.getIndirizzo());

	    speR.save(sped);
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(SpedizioniReq req) throws ZooException {
		log.debug("update {}", req);

	    if (req.getId() == null)
	        throw new ZooException("Id spedizione mancante");

	    Spedizioni sped = speR.findById(req.getId())
	            .orElseThrow(() -> new ZooException("Spedizione non trovata"));

	    if (req.getTrackingNumber() != null)
	        sped.setTrackingNumber(req.getTrackingNumber());

	    if (req.getStato() != null)
	        sped.setStato(StatoSpedizione.valueOf(req.getStato().toUpperCase()));

	    if (req.getCosto() != null)
	        sped.setCosto(req.getCosto());

	    if (req.getDataAggiornamento() != null)
	        sped.setDataAggiornamento(req.getDataAggiornamento());

	    if (req.getCorriereId() != null) {
	        Corrieri corriere = corR.findById(req.getCorriereId())
	                .orElseThrow(() -> new ZooException("Corriere non trovato"));

	        sped.setCorriere(corriere);
	    }
	    
	    //Possibilità di cambiare in dirizzo in fase non di attesa

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

	@Override
	public SpedizioniDTO getByOrdineId(Integer id) throws Exception {
	    log.debug("getByOrdineId {}", id);

	    Spedizioni sped = speR.findByOrdine_Id(id)
	            .orElseThrow(() -> new ZooException("Spedizione non trovata"));

	    return Mapper.buildSpedizioniDTO(sped);
	}
}
