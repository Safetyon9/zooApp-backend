package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.OggettiCarrelliReq;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Items;
import com.betacom.persistence.entity.commerce.OggettiCarrelli;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.persistence.repository.commerce.IOggettiCarrelliRepository;
import com.betacom.services.interfaces.commerce.IOggettiCarrelliServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OggettiCarrelliImpl implements IOggettiCarrelliServices{
	
	private final IOggettiCarrelliRepository oggRepo;
	private final ICarrelliRepository carrRepo;
	private final IItemsRepository itemRepo;
	//private final IMessaggiServices msgS;
	
	@Override
	public void create(OggettiCarrelliReq req) throws Exception {
		log.debug("create {}", req);
		
		Carrelli c = carrRepo.findById(req.getId())
				.orElseThrow(() -> new ZooException("carrello non trovato nel DB: "+ req.getCarrelloId()));
		
		Items item = itemRepo.findById(req.getItemId())
		        .orElseThrow(() -> new ZooException("item non trovato nel DB: " + req.getItemId()));
		
		OggettiCarrelli oggettiCarrelli = new OggettiCarrelli();
		
		oggettiCarrelli.setPrezzoTotale(req.getPrezzoTotale());
		oggettiCarrelli.setQuantita(req.getQuantita());
		oggettiCarrelli.setCarrello(c);
		oggettiCarrelli.setItem(item);
		
		oggRepo.save(oggettiCarrelli);
		
	}

	@Override
	public void update(OggettiCarrelliReq req) throws Exception {
		log.debug("update {}", req);
		
		OggettiCarrelli oggettiCarrelli = oggRepo.findById(req.getId())
				.orElseThrow(() -> new ZooException("oggetto carrello non trovato nel DB: "+ req.getId()));
		
		if(req.getPrezzoTotale() != null)
			oggettiCarrelli.setPrezzoTotale(req.getPrezzoTotale());
		
		if(req.getQuantita() != null)
			oggettiCarrelli.setQuantita(req.getQuantita());
		
		oggRepo.save(oggettiCarrelli);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		
		OggettiCarrelli oggettiCarrelli = oggRepo.findById(id)
				.orElseThrow(() -> new ZooException("oggetto carrello non trovato nel DB: "+ id));
		
		oggRepo.delete(oggettiCarrelli);
		
	}

	@Override
	public List<OggettiCarrelliDTO> findAll() throws Exception {
		List<OggettiCarrelli> lOggC = oggRepo.findAll();
		return lOggC.stream()
				.map(oc -> OggettiCarrelliDTO.builder()
						.id(oc.getId())
						.prezzoTotale(oc.getPrezzoTotale())
						.quantita(oc.getQuantita())
						.carrelloId(oc.getCarrello().getId())  
				        .itemId(oc.getItem().getId())
						.build())
				.toList();
	}

	@Override
	public OggettiCarrelliDTO getById(Integer id) throws Exception {
		OggettiCarrelli oggettiCarrelli = oggRepo.findById(id)
				.orElseThrow(() -> new ZooException("oggetto carrello non trovato nel DB: "+ id));
		
		
		return OggettiCarrelliDTO.builder()
				.id(oggettiCarrelli.getId())
				.prezzoTotale(oggettiCarrelli.getPrezzoTotale())
				.quantita(oggettiCarrelli.getQuantita())
				.carrelloId(oggettiCarrelli.getCarrello().getId())
		        .itemId(oggettiCarrelli.getItem().getId())
				.build();
	}

}
