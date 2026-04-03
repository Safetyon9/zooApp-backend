package com.betacom.services.implementations.commerce.items;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.BigliettiGiornateReq;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiGiornataRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IBigliettiGiornataServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BigliettiGiornataImpl implements IBigliettiGiornataServices{

	private final IBigliettiGiornataRepository speR;
	private final IMessaggiServices msgS;
	
	private final IBigliettiRepository bigR;
	private final IGiornateRepository gioR;
	private final IEventiRepository eveR;
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void create(BigliettiGiornateReq req) throws ZooException {
		log.debug("create {}", req);

		if (!bigR.existsById(req.getBigliettoId())) {
		    throw new ZooException("Biglietto non trovato");
		}
		if (!gioR.existsById(req.getGiornataId())) {
		    throw new ZooException("Giornata non trovata");
		}
		if (req.getEventoId() != null && !eveR.existsById(req.getEventoId())) {
		    throw new ZooException("Evento non trovato");
		}
	    if (req.getPrezzo() == null)
	        throw new ZooException("Prezzo non trovato.");
	    if (req.getStock() == null)
	        throw new ZooException("Stock non trovato.");

	    Biglietti biglietto = bigR.findById(req.getBigliettoId())
	            .orElseThrow(() -> new ZooException("Biglietto non trovato nel DB"));

	    Giornate giornata = gioR.findById(req.getGiornataId())
	            .orElseThrow(() -> new ZooException("Giornata non trovata nel DB"));

	    Eventi evento = null;
	    if (req.getEventoId() != null) {
	        evento = eveR.findById(req.getEventoId())
	                .orElseThrow(() -> new ZooException("Evento non trovato nel DB"));
	    }

	    BigliettiGiornata bg = new BigliettiGiornata();
	    bg.setBiglietto(biglietto);
	    bg.setGiornata(giornata);
	    bg.setEvento(evento);
	    bg.setPrezzo(req.getPrezzo());
	    bg.setStock(req.getStock());

	    speR.save(bg);
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(BigliettiGiornateReq req) throws Exception {
		log.debug("update {}", req);

	    BigliettiGiornata bg = speR.findById(req.getId())
	            .orElseThrow(() -> new ZooException("BigliettoGiornata non trovato"));

	    if (req.getPrezzo() != null)
	        bg.setPrezzo(req.getPrezzo());

	    if (req.getStock() != null)
	        bg.setStock(req.getStock());

	    if (req.getEventoId() != null) {
	        Eventi evento = eveR.findById(req.getEventoId())
	                .orElseThrow(() -> new ZooException("Evento non trovato"));
	        bg.setEvento(evento);
	    }

	    speR.save(bg);
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);

	    BigliettiGiornata bg = speR.findById(id)
	            .orElseThrow(() -> new ZooException("BigliettoGiornata non trovato"));

	    speR.delete(bg);
	}
	
	@Override
	public List<BigliettiGiornateDTO> list() {
		log.debug("list");

	    List<BigliettiGiornata> lB = speR.findAll();
	    return lB.stream()
	    		.map(b -> Mapper.buildBigliettiGiornateDTO(b))
	    		.collect(Collectors.toList());
	}
	
	@Override
	public BigliettiGiornateDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);

	    BigliettiGiornata bg = speR.findById(id)
	            .orElseThrow(() -> new ZooException("BigliettoGiornata non trovato"));

	    return Mapper.buildBigliettiGiornateDTO(bg);
	}
}

