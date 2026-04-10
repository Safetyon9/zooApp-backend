package com.betacom.services.implementations.commerce;

<<<<<<< Updated upstream
import java.util.ArrayList;
=======
>>>>>>> Stashed changes
import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.CarrelliReq;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
<<<<<<< Updated upstream
import com.betacom.persistence.entity.commerce.OggettiCarrelli;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.ICarrelliServices;
import com.betacom.utilities.Mapper;
=======
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.commerce.ICarrelliServices;
>>>>>>> Stashed changes

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarrelliImpl implements ICarrelliServices{
	
	private final ICarrelliRepository carrelliRepo;
	private final IClientiRepository clientiRepo;
<<<<<<< Updated upstream
	private final IMessaggiServices msgS;
=======
	//private final IMessaggiServices msgS;
>>>>>>> Stashed changes
	
	@Override
	public void create(CarrelliReq req) throws Exception {
		log.debug("create {}", req);
		
		
		Clienti cliente = clientiRepo.findById(req.getClienteId())
	            .orElseThrow(() -> new ZooException("cliente non trovato nel DB: " + req.getClienteId()));
		
		if(carrelliRepo.findByClienteId(req.getClienteId()).isPresent())
	        throw new ZooException("il cliente ha già un carrello: " + req.getClienteId());
		
		Carrelli c = new Carrelli();
		
<<<<<<< Updated upstream
		c.setCliente(cliente);
		c.setOggettiCarrello(new ArrayList<OggettiCarrelli>());
		 
		carrelliRepo.save(c);
=======
		 c.setCliente(cliente);
		 
		 carrelliRepo.save(c);
>>>>>>> Stashed changes
		
	}
	
	
	//Pensare l'update

	@Override
	public void delete(Integer id) throws Exception {
		
		Carrelli c = carrelliRepo.findById(id)
				.orElseThrow(() -> new ZooException("Carrello non trovato nel DB"));
		
		carrelliRepo.delete(c);
	}

	@Override
	public List<CarrelliDTO> findAll() throws Exception {
<<<<<<< Updated upstream
		List<Carrelli> lC = carrelliRepo.findAll();
		
		return lC.stream()
				.map(c -> Mapper.buildCarrelliDTO(c)).toList();
=======
		// TODO Auto-generated method stub
		return null;
>>>>>>> Stashed changes
	}

	@Override
	public CarrelliDTO getById(Integer id) throws Exception {
<<<<<<< Updated upstream
		
		Carrelli carrelli = carrelliRepo.findById(id)
				.orElseThrow(() -> new ZooException("carrello non trovato nel DB: "));
		
		return Mapper.buildCarrelliDTO(carrelli);
=======
		// TODO Auto-generated method stub
		return null;
>>>>>>> Stashed changes
	}

}
