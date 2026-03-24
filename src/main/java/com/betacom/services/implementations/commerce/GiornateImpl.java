package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.GiornateReq;
import com.betacom.dto.outputs.commerce.GiornateDTO;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.services.interfaces.commerce.IGiornateServices;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.utilities.Mapper;

@Service
public class GiornateImpl implements IGiornateServices {

    @Autowired
    private IGiornateRepository repo;

    @Autowired
    private IEventiRepository evE;

    @Override
    public void create(GiornateReq req) throws Exception {
        Giornate g = new Giornate();

        g.setData(req.getData());

       
        Eventi e = evE.findById(req.getEventoId())
                .orElseThrow(() -> new Exception("Evento non trovato"));

        g.setEvento(e);

        repo.save(g);
    }

    @Override
    public void update(GiornateReq req) throws Exception {
        Giornate g = repo.findById(req.getId())
                .orElseThrow(() -> new Exception("Giornata non trovata"));

        g.setData(req.getData());

      
        Eventi e = evE.findById(req.getEventoId())
                .orElseThrow(() -> new Exception("Evento non trovato"));

        g.setEvento(e);

        repo.save(g);
    }

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