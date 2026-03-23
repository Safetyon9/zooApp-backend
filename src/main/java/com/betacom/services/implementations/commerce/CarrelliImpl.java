package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.CarrelliReq;
import com.betacom.dto.outputs.commerce.CarrelliDTO;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.commerce.ICarrelliServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarrelliImpl implements ICarrelliServices{
	
	private final ICarrelliRepository carrelliRepo;
	private final IClientiRepository clientiRepo;
	//private final IMessaggiServices msgS;
	
	@Override
	public void create(CarrelliReq req) throws Exception {
		log.debug("create {}", req);
		
		
		Clienti cliente = clientiRepo.findById(req.getClienteId())
	            .orElseThrow(() -> new ZooException("cliente non trovato nel DB: " + req.getClienteId()));
		
		if(carrelliRepo.findByClienteId(req.getClienteId()).isPresent())
	        throw new ZooException("il cliente ha già un carrello: " + req.getClienteId());
		
		Carrelli c = new Carrelli();
		
		 c.setCliente(cliente);
		 
		 carrelliRepo.save(c);
		
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
		List<Carrelli> lC = carrelliRepo.findAll();
		
		return lC.stream()
				.map(c -> CarrelliDTO.builder()
		        .id(c.getId())
		        .cliente(ClientiDTO.builder()
		                .id(c.getCliente().getId())
		                .email(c.getCliente().getEmail())
		                .nome(c.getCliente().getNome())
		                .cognome(c.getCliente().getCognome())
		                .indirizzo(c.getCliente().getIndirizzo())
		                //aggiungere altri campi cliente (?)
		                .build())
		        .oggettiCarrello(c.getOggettiCarrello().stream()
		                .map(oc -> OggettiCarrelliDTO.builder()
		                        .id(oc.getId())
		                        .quantita(oc.getQuantita())
		                        .prezzoTotale(oc.getPrezzoTotale())
		                        .build())
		                .toList())
		        .build()).toList();
	}

	@Override
	public CarrelliDTO getById(Integer id) throws Exception {
		
		Carrelli carrelli = carrelliRepo.findById(id)
				.orElseThrow(() -> new ZooException("carrello non trovato nel DB: "));
		
		return CarrelliDTO.builder()
		        .id(carrelli.getId())
		        .cliente(ClientiDTO.builder()
		                .id(carrelli.getCliente().getId())
		                .email(carrelli.getCliente().getEmail())
		                .nome(carrelli.getCliente().getNome())
		                .cognome(carrelli.getCliente().getCognome())
		                .indirizzo(carrelli.getCliente().getIndirizzo())
		                //aggiungere altri campi cliente (?)
		                .build())
		        .oggettiCarrello(carrelli.getOggettiCarrello().stream()
		                .map(oc -> OggettiCarrelliDTO.builder()
		                        .id(oc.getId())
		                        .quantita(oc.getQuantita())
		                        .prezzoTotale(oc.getPrezzoTotale())
		                        .build())
		                .toList())
		        .build();
	}

}
