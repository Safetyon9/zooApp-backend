package com.betacom.services.implementations.commerce;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.commerce.items.IClientiServices;
import com.betacom.utilities.Mapper;

@Service
public class ClientiImpl implements IClientiServices {

    @Autowired
    private IClientiRepository repoC;

    @Override
    public void create(ClientiReq req) throws Exception {
        Clienti c = new Clienti();

        c.setEmail(req.getEmail());
        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());

        repoC.save(c);
    }

    @Override
    public void update(ClientiReq req) throws Exception {
        Clienti c = repoC.findById(req.getUtenteId())
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        c.setEmail(req.getEmail());
        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());
        c.setId(req.getUtenteId());

        repoC.save(c);
    }

    @Override
    public void delete(Integer id) throws Exception {
    	
    	Clienti c = repoC.findById(id)
				.orElseThrow(() -> new ZooException("Cliente non trovato nel DB"));
		
    	repoC.delete(c);
    }

    @Override
    public List<ClientiDTO> findAll() {
    	List<Clienti> lC = repoC.findAll();
    	
        return lC.stream()
               .map(c -> Mapper.buildClienteDTO(c))
               .toList();
    }

    @Override
    public ClientiDTO getById(Integer id) throws Exception {
        Clienti c = repoC.findById(id)
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        return Mapper.buildClienteDTO(c);
    }
}