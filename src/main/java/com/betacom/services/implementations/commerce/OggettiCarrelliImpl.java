package com.betacom.services.implementations.commerce;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.OggettiCarrelliReq;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.OggettiCarrelli;
import com.betacom.persistence.entity.commerce.items.Items;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.persistence.repository.commerce.IOggettiCarrelliRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.IOggettiCarrelliServices;
import com.betacom.utilities.Mapper;
import com.betacom.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OggettiCarrelliImpl implements IOggettiCarrelliServices{
	
	private final IOggettiCarrelliRepository oggRepo;
	private final ICarrelliRepository carrRepo;
	private final IItemsRepository itemRepo;
	private final IMessaggiServices msgS;
	

    @Transactional(rollbackFor = ZooException.class)
	@Override
	public void create(OggettiCarrelliReq req) throws Exception {
		log.debug("create {}", req);
		
		Carrelli c = carrRepo.findById(req.getCarrelloId())
				.orElseThrow(() -> new ZooException("carrello non trovato nel DB: "+ req.getCarrelloId()));
		
		Items item = itemRepo.findById(req.getItemId())
		        .orElseThrow(() -> new ZooException("item non trovato nel DB: " + req.getItemId()));
		
		Optional<OggettiCarrelli> existing =
				oggRepo.findByCarrelloIdAndItemId(req.getCarrelloId(), req.getItemId());
		
		OggettiCarrelli obj;

		if (existing.isPresent()) {

		    obj = existing.get();

		    obj.setQuantita(obj.getQuantita() + req.getQuantita());

		} else {

		    obj = new OggettiCarrelli();
		    obj.setCarrello(c);
		    obj.setItem(item);
		    obj.setPrezzoUnitario(item.getPrezzo());
		    obj.setQuantita(req.getQuantita());
		}

		obj.setPrezzoTotale(
		    Utils.calcolaPrezzoTotale(obj.getQuantita(), obj.getPrezzoUnitario())
		);

		oggRepo.save(obj);
		
	}


    @Transactional(rollbackFor = ZooException.class)
	@Override
	public void update(OggettiCarrelliReq req) throws Exception {
		log.debug("update {}", req);
		
		OggettiCarrelli oggettiCarrelli = oggRepo.findById(req.getId())
				.orElseThrow(() -> new ZooException("oggetto carrello non trovato nel DB: "+ req.getId()));
		
		if(req.getPrezzoTotale() != null)
			oggettiCarrelli.setPrezzoTotale(
				    Utils.calcolaPrezzoTotale(
				        oggettiCarrelli.getQuantita(),
				        oggettiCarrelli.getPrezzoUnitario()
				    )
				);
		
		if(req.getQuantita() != null)
			oggettiCarrelli.setQuantita(req.getQuantita());
		
		oggRepo.save(oggettiCarrelli);
		
	}


    @Transactional(rollbackFor = ZooException.class)
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
				.map(oc -> Mapper.buildOggettiCarrelliDTO(oc))
				.toList();
	}

	@Override
	public OggettiCarrelliDTO getById(Integer id) throws Exception {
		OggettiCarrelli oggettiCarrelli = oggRepo.findById(id)
				.orElseThrow(() -> new ZooException("oggetto carrello non trovato nel DB: "+ id));
		
		return Mapper.buildOggettiCarrelliDTO(oggettiCarrelli);
		
	}

}
