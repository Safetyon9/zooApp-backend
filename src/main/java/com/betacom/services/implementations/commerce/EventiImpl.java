package com.betacom.services.implementations.commerce;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.persistence.entity.commerce.Eventi;

import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.services.interfaces.commerce.items.IEventiServices;
import com.betacom.utilities.Mapper;

@Service
public class EventiImpl implements IEventiServices {

    @Autowired
    private IEventiRepository repo;

    @Override
    public void create(EventiReq req) throws Exception {
        Eventi e = new Eventi();

        e.setTipoEvento(req.getTipoEvento());
        e.setDataInizio(req.getDataInizio());
        e.setDataFine(req.getDataFine());

        repo.save(e);
    }

    @Override
    public void update(EventiReq req) throws Exception {
        Eventi e = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Evento non trovato"));

        e.setTipoEvento(req.getTipoEvento());
        e.setDataInizio(req.getDataInizio());
        e.setDataFine(req.getDataFine());

        repo.save(e);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public List<EventiDTO> findAll() throws Exception {
        return repo.findAll()
                .stream()
                .map(Mapper::buildEventoDTO)
                .toList();
    }

    @Override
    public EventiDTO getById(Integer id) throws Exception {
        Eventi e = repo.findById(id)
                .orElseThrow(() -> new Exception("Evento non trovato"));

        return Mapper.buildEventoDTO(e);
    }
}