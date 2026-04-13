package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.commerce.EventiDTO;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.specification.EventiSpecification;
import com.betacom.persistence.specification.UtentiSpecification;
import com.betacom.services.interfaces.commerce.IEventiServices;
import com.betacom.utilities.Mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventiImpl implements IEventiServices {

    @Autowired
    private IEventiRepository repo;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(EventiReq req) throws Exception {

        if (req.getTipoEvento() == null || req.getTipoEvento().isBlank())
            throw new Exception("Tipo evento obbligatorio");

        if (req.getDataInizio() == null)
            throw new Exception("Data inizio obbligatoria");

        if (req.getDataFine().isBefore(req.getDataInizio()))
            throw new Exception("Data fine non può essere prima della data inizio");
        
        
        Eventi e = new Eventi();
        e.setDescrizione(req.getDescrizione());
        e.setTipoEvento(req.getTipoEvento());
        e.setDataInizio(req.getDataInizio());
        e.setDataFine(req.getDataFine());

        repo.save(e);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(EventiReq req) throws Exception {

        if (req.getId() == null || req.getId() <= 0)
            throw new Exception("Id evento non valido");

        Eventi e = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Evento non trovato"));

        if (req.getTipoEvento() != null && !req.getTipoEvento().isBlank()) {
            e.setTipoEvento(req.getTipoEvento());
        }

        if (req.getDataInizio() != null) {
            e.setDataInizio(req.getDataInizio());
        }

        if (req.getDataFine() != null) {
            e.setDataFine(req.getDataFine());
        }
        if(req.getDescrizione() != null) {
        	e.setDescrizione(req.getDescrizione());
        }

        if (e.getDataFine() != null && e.getDataInizio() != null &&
                e.getDataFine().isBefore(e.getDataInizio())) {

            throw new Exception("Data fine non può essere prima della data inizio");
        }

        repo.save(e);
    
        
         }

     

    @Transactional(rollbackFor = Exception.class)
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
    
    
    @Override
    public List<EventiDTO> find(EventiReq req) {

        log.debug("find con filtri {}", req);

        return repo.findAll(EventiSpecification.filterByParams(req))
                .stream()
                .map(Mapper::buildEventoDTO)
                .toList();
    }
    
    
}