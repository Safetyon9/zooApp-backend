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
import com.betacom.utilities.Mapper;

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
			log.debug("update {}", req);

		    if (req.getId() == null)
		        throw new ZooException("Id recensione non fornito");

		    Recensioni rec = speR.findById(req.getId())
		            .orElseThrow(() -> new ZooException("Recensione non trovata"));

		    if (req.getVoto() != null)
		        rec.setVoto(req.getVoto());

		    if (req.getTesto() != null)
		        rec.setTesto(req.getTesto());

		    if (req.getTitolo() != null)
		        rec.setTitolo(req.getTitolo());

		    if (req.getGeneraleZoo() != null)
		        rec.setGeneraleZoo(req.getGeneraleZoo());

		    if (req.getClienteId() != null) {
		        Clienti cliente = clientiR.findById(req.getClienteId())
		                .orElseThrow(() -> new ZooException("Cliente non trovato"));
		        rec.setCliente(cliente);
		    }

		    if (req.getItemId() != null) {
		        Items item = itemsR.findById(req.getItemId())
		                .orElseThrow(() -> new ZooException("Item non trovato"));
		        rec.setItem(item);
		    }

		    speR.save(rec);
		}
		
		@Transactional (rollbackFor = ZooException.class)
		@Override
		public void delete(Integer id) throws Exception {
		    log.debug("delete {}", id);

		    Recensioni rec = speR.findById(id)
		            .orElseThrow(() -> new ZooException("Recensione non trovata"));

		    speR.delete(rec);
		}
		
		@Override
		public List<RecensioniDTO> list() {
			log.debug("list");

		    List<Recensioni> list = speR.findAll();

		    return list.stream()
		            .map(r -> Mapper.buildRecensioniDTO(r))
		            .toList();
		}
		
		@Override
		public RecensioniDTO getById(Integer id) throws Exception {
			log.debug("getById {}", id);

		    Recensioni rec = speR.findById(id)
		            .orElseThrow(() -> new ZooException("Recensione non trovata"));

		    return Mapper.buildRecensioniDTO(rec);
		}
	}
