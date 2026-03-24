package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.commerce.items.IClientiServices;
import com.betacom.utilities.Mapper;

@Service
public class ClientiImpl implements IClientiServices {

    @Autowired
    private IClientiRepository repo;

    @Autowired
    private IUtentiRepository utentiRepo;

    @Override
    public void create(ClientiReq req) throws Exception {
        Clienti c = new Clienti();

        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());

      
        Utenti u = utentiRepo.findById(req.getUtenteId())
                .orElseThrow(() -> new Exception("Utente non trovato"));

        c.setUtente(u);

        repo.save(c);
    }

    @Override
    public void update(ClientiReq req) throws Exception {
        Clienti c = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());

      
        Utenti u = utentiRepo.findById(req.getUtenteId())
                .orElseThrow(() -> new Exception("Utente non trovato"));

        c.setUtente(u);

        repo.save(c);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public List<ClientiDTO> findAll() throws Exception {
        return repo.findAll()
                .stream()
                .map(Mapper::buildClienteDTO)
                .toList();
    }

    @Override
    public ClientiDTO getById(Integer id) throws Exception {
        Clienti c = repo.findById(id)
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        return Mapper.buildClienteDTO(c);
    }
}