package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.RecensioniReq;
import com.betacom.dto.outputs.commerce.RecensioniDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.Recensioni;
import com.betacom.persistence.entity.commerce.items.Items;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.persistence.repository.commerce.IRecensioniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.IRecensioniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecensioniImpl implements IRecensioniServices{
	
	private final IRecensioniRepository speR;
	private final IMessaggiServices msgS;
	
	private final IClientiRepository clientiR;
	private final IItemsRepository itemsR;
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void create(RecensioniReq req) throws ZooException {
		log.debug("create {}", req);

	    if (req.getVoto() == null)
	        throw new ZooException("Voto non trovato.");
	    if (req.getClienteId() == null)
	        throw new ZooException("Cliente non trovato.");
	    if (req.getItemId() == null)
	        throw new ZooException("Item non trovato.");

	    Clienti cliente = clientiR.findById(req.getClienteId())
	            .orElseThrow(() -> new ZooException("Cliente non trovato nel DB"));

	    Items item = itemsR.findById(req.getItemId())
	            .orElseThrow(() -> new ZooException("Item non trovato nel DB"));

	    Recensioni rec = new Recensioni();
	    rec.setVoto(req.getVoto());
	    rec.setTesto(req.getTesto());
	    rec.setTitolo(req.getTitolo());
	    rec.setGeneraleZoo(req.getGeneraleZoo());
	    rec.setCliente(cliente);
	    rec.setItem(item);

	    speR.save(rec);
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(RecensioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<RecensioniDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public RecensioniDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
