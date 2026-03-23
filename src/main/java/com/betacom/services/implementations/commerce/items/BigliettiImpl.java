package com.betacom.services.implementations.commerce.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.services.interfaces.commerce.items.IBigliettiServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BigliettiImpl implements IBigliettiServices {

    private final IBigliettiRepository bigliettiR;

    public BigliettiImpl(IBigliettiRepository bigliettiR) {
        this.bigliettiR = bigliettiR;
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(BigliettiReq req) throws Exception {
        log.debug("create {}", req);

        Biglietti b = new Biglietti();

        b.setNome(req.getNome());
        b.setDescrizione(req.getDescrizione());
        b.setUrlImmagine(req.getUrlImmagine());
        b.setPrezzo(req.getPrezzo());
        b.setTipo(req.getTipo());

        bigliettiR.save(b);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(BigliettiReq req) throws Exception {
        log.debug("update {}", req);

        Biglietti b = bigliettiR.findById(req.getId())
                .orElseThrow(() -> new ZooException("Biglietto non trovato"));

        b.setNome(req.getNome());
        b.setDescrizione(req.getDescrizione());
        b.setUrlImmagine(req.getUrlImmagine());
        b.setPrezzo(req.getPrezzo());
        b.setTipo(req.getTipo());

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

        BigliettiDTO dto = new BigliettiDTO();

        dto.setId(b.getId());
        dto.setNome(b.getNome());
        dto.setDescrizione(b.getDescrizione());
        dto.setUrlImmagine(b.getUrlImmagine());
        dto.setPrezzo(b.getPrezzo());
        dto.setTipo(b.getTipo());

        return dto;
    }

    @Override
    public List<BigliettiDTO> findAll() throws Exception {
        log.debug("findAll");

        List<Biglietti> lista = bigliettiR.findAll();
        List<BigliettiDTO> listaDTO = new ArrayList<>();

        for (Biglietti b : lista) {

            BigliettiDTO dto = new BigliettiDTO();

            dto.setId(b.getId());
            dto.setNome(b.getNome());
            dto.setDescrizione(b.getDescrizione());
            dto.setUrlImmagine(b.getUrlImmagine());
            dto.setPrezzo(b.getPrezzo());
            dto.setTipo(b.getTipo());

            listaDTO.add(dto);
        }

        return listaDTO;
    }
}