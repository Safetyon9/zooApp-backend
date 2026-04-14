package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.GiornateReq;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.services.interfaces.commerce.IGiornateServices;
import com.betacom.utilities.Mapper;

@Service
public class GiornateImpl implements IGiornateServices {

    @Autowired
    private IGiornateRepository repo;

    @Autowired
    private IEventiRepository evE;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(GiornateReq req) throws Exception {

        if (req.getData() == null)
            throw new Exception("Data obbligatoria");

        if (req.getStock() == null)
            throw new Exception("Stock obbligatorio");

        if (req.getAperto() == null)
            throw new Exception("Condizione obbligatoria");

        Giornate g = new Giornate();
        g.setData(req.getData());
        g.setStock(req.getStock());
        g.setAperto(req.getAperto());

        if (req.getEventoId() != null && req.getEventoId() > 0) {
            Eventi e = evE.findById(req.getEventoId())
                    .orElseThrow(() -> new Exception("Evento non trovato"));
            g.setEvento(e);
        }

        repo.save(g);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GiornateReq req) throws Exception {

        if (req.getId() == null || req.getId() <= 0)
            throw new Exception("Id giornata non valido");

        Giornate g = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Giornata non trovata"));

        if (req.getData() != null) {
            g.setData(req.getData());
        }

        if (req.getEventoId() != null && req.getEventoId() > 0) {
            Eventi e = evE.findById(req.getEventoId())
                    .orElseThrow(() -> new Exception("Evento non trovato"));
            g.setEvento(e);
        } else if (req.getEventoId() != null && req.getEventoId() <= 0) {
            g.setEvento(null);
        }

        if (req.getStock() != null) {
            if (req.getStock() < 0)
                throw new Exception("Stock non valido");

            g.setStock(req.getStock());
        }

        if (req.getAperto() != null) {
            g.setAperto(req.getAperto());
        }

        repo.save(g);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public List<GiornateDTO> findAll() throws Exception {
        return repo.findAll()
                .stream()
                .map(Mapper::buildGiornataDTO)
                .toList();
    }

    @Override
    public GiornateDTO getById(Integer id) throws Exception {
        Giornate g = repo.findById(id)
                .orElseThrow(() -> new Exception("Giornata non trovata"));

        return Mapper.buildGiornataDTO(g);
    }
}