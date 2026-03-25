package com.betacom.services.implementations.commerce.items;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.services.interfaces.commerce.items.IBigliettiServices;
import com.betacom.utilities.Mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BigliettiImpl implements IBigliettiServices {

    private final IBigliettiRepository bigliettiR;
    private final ITipiBigliettiRepository tipoRepo;

    public BigliettiImpl(IBigliettiRepository bigliettiR,ITipiBigliettiRepository tipoRepo) {
        this.bigliettiR = bigliettiR;
        this.tipoRepo = tipoRepo;
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(BigliettiReq req) throws Exception {
    	
    	
        log.debug("create {}", req);


        if (req.getNome() == null || req.getNome().isBlank())
            throw new ZooException("Nome obbligatorio");

        if (req.getPrezzo() == null || req.getPrezzo().compareTo(BigDecimal.ZERO) <= 0)
            throw new ZooException("Prezzo non valido");
        
        if (req.getTipoId() == null)
            throw new ZooException("Tipo obbligatorio");
        
        TipiBiglietti tipo = tipoRepo.findById(req.getTipoId())
                .orElseThrow(() -> new ZooException("Tipo non valido"));
        
        if (req.getUrlImmagine() == null || req.getUrlImmagine().isBlank())
            throw new ZooException("Immagine obbligatoria");
        
        
        Biglietti b = new Biglietti();

        b.setNome(req.getNome());
        b.setDescrizione(req.getDescrizione());
        b.setUrlImmagine(req.getUrlImmagine());
        b.setPrezzo(req.getPrezzo());
        b.setTipo(tipo);

        bigliettiR.save(b);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(BigliettiReq req) throws Exception {
        log.debug("update {}", req);
        

        Biglietti b = bigliettiR.findById(req.getId())
                .orElseThrow(() -> new ZooException("Biglietto non trovato"));

        
        if (req.getId() == null || req.getId() <= 0)
            throw new ZooException("Id non valido");

        if (req.getNome() != null && !req.getNome().isBlank()) {
            b.setNome(req.getNome());
        }
        if (req.getPrezzo() != null && req.getPrezzo().compareTo(BigDecimal.ZERO) > 0) {
            b.setPrezzo(req.getPrezzo());
        }
        
        if (req.getTipoId() != null) {
            TipiBiglietti tipo = tipoRepo.findById(req.getTipoId())
                    .orElseThrow(() -> new ZooException("Tipo non valido"));
            b.setTipo(tipo);
        }   
        if (req.getUrlImmagine() != null && !req.getUrlImmagine().isBlank()) {
            b.setUrlImmagine(req.getUrlImmagine());
        }

         bigliettiR.save(b);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);

        Biglietti b = bigliettiR.findById(id)
                .orElseThrow(() -> new ZooException("Biglietto non trovato"));

        bigliettiR.delete(b);
    }

    @Override
    public BigliettiDTO getById(Integer id) throws Exception {
        log.debug("getById {}", id);

        Biglietti b = bigliettiR.findById(id)
                .orElseThrow(() -> new ZooException("Biglietto non trovato"));

        return Mapper.buildBigliettiDTO(b);
    }

    @Override
    public List<BigliettiDTO> findAll() throws Exception {
        log.debug("findAll");

        List<Biglietti> lista = bigliettiR.findAll();
        List<BigliettiDTO> listaDTO = new ArrayList<>();

        for (Biglietti b : lista) {

         
            listaDTO.add(Mapper.buildBigliettiDTO(b));
        }

        return listaDTO;
    }
}