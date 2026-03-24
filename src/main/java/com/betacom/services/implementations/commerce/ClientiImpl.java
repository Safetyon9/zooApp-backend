package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(ClientiReq req) throws Exception {

        if (req.getNome() == null || req.getNome().isBlank())
            throw new Exception("Nome obbligatorio");

        if (req.getCognome() == null || req.getCognome().isBlank())
            throw new Exception("Cognome obbligatorio");

        if (req.getIndirizzo() == null || req.getIndirizzo().isBlank())
            throw new Exception("Indirizzo obbligatorio");

        if (req.getUtenteId() == null || req.getUtenteId() <= 0)
            throw new Exception("UtenteId non valido");

        Utenti u = utentiRepo.findById(req.getUtenteId())
                .orElseThrow(() -> new Exception("Utente non trovato"));

        Clienti c = new Clienti();
        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());
        c.setUtente(u);

        repo.save(c);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ClientiReq req) throws Exception {

        if (req.getId() == null || req.getId() <= 0)
            throw new Exception("Id cliente non valido");

        if (req.getNome() == null || req.getNome().isBlank())
            throw new Exception("Nome obbligatorio");

        if (req.getCognome() == null || req.getCognome().isBlank())
            throw new Exception("Cognome obbligatorio");

        if (req.getIndirizzo() == null || req.getIndirizzo().isBlank())
            throw new Exception("Indirizzo obbligatorio");

        if (req.getUtenteId() == null || req.getUtenteId() <= 0)
            throw new Exception("UtenteId non valido");

        Clienti c = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        Utenti u = utentiRepo.findById(req.getUtenteId())
                .orElseThrow(() -> new Exception("Utente non trovato"));

        c.setNome(req.getNome());
        c.setCognome(req.getCognome());
        c.setIndirizzo(req.getIndirizzo());
        c.setUtente(u);

        repo.save(c);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null || id <= 0)
            throw new Exception("Id non valido");

        Clienti c = repo.findById(id)
                .orElseThrow(() -> new Exception("Cliente non trovato"));

        repo.delete(c);
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