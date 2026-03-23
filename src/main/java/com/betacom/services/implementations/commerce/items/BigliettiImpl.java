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
    public BigliettiDTO create(BigliettiReq req) throws Exception {
        log.debug("create {}", req);

        Biglietti biglietto = new Biglietti();
        biglietto.setNome(req.getNome());
        biglietto.setDescrizione(req.getDescrizione());
        biglietto.setUrlImmagine(req.getUrlImmagine());
        biglietto.setPrezzo(req.getPrezzo());
        biglietto.setTipo(req.getTipo());

        biglietto = bigliettiR.save(biglietto);

        BigliettiDTO dto = new BigliettiDTO();
        dto.setId(biglietto.getId());
        dto.setNome(biglietto.getNome());
        dto.setDescrizione(biglietto.getDescrizione());
        dto.setUrlImmagine(biglietto.getUrlImmagine());
        dto.setPrezzo(biglietto.getPrezzo());
        dto.setTipo(biglietto.getTipo());

        return dto;
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public BigliettiDTO update(Integer id, BigliettiReq req) throws Exception {
        log.debug("update {} {}", id, req);

        Biglietti biglietto = bigliettiR.findById(id)
                .orElseThrow(() -> new ZooException("Biglietto non trovato in DB: " + id));

        biglietto.setNome(req.getNome());
        biglietto.setDescrizione(req.getDescrizione());
        biglietto.setUrlImmagine(req.getUrlImmagine());
        biglietto.setPrezzo(req.getPrezzo());
        biglietto.setTipo(req.getTipo());

        biglietto = bigliettiR.save(biglietto);

        BigliettiDTO dto = new BigliettiDTO();
        dto.setId(biglietto.getId());
        dto.setNome(biglietto.getNome());
        dto.setDescrizione(biglietto.getDescrizione());
        dto.setUrlImmagine(biglietto.getUrlImmagine());
        dto.setPrezzo(biglietto.getPrezzo());
        dto.setTipo(biglietto.getTipo());

        return dto;
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);

        Biglietti biglietto = bigliettiR.findById(id)
                .orElseThrow(() -> new ZooException("Biglietto non trovato in DB: " + id));

        bigliettiR.delete(biglietto);
    }

    @Override
    public BigliettiDTO getById(Integer id) throws Exception {
        log.debug("getById {}", id);

        Biglietti biglietto = bigliettiR.findById(id)
                .orElseThrow(() -> new ZooException("Biglietto non trovato in DB: " + id));

        BigliettiDTO dto = new BigliettiDTO();
        dto.setId(biglietto.getId());
        dto.setNome(biglietto.getNome());
        dto.setDescrizione(biglietto.getDescrizione());
        dto.setUrlImmagine(biglietto.getUrlImmagine());
        dto.setPrezzo(biglietto.getPrezzo());
        dto.setTipo(biglietto.getTipo());

        return dto;
    }

    @Override
    public List<BigliettiDTO> getAll() throws Exception {
        log.debug("getAll");

        List<Biglietti> lista = bigliettiR.findAll();
        List<BigliettiDTO> dtoList = new ArrayList<>();

        for (Biglietti biglietto : lista) {
            BigliettiDTO dto = new BigliettiDTO();
            dto.setId(biglietto.getId());
            dto.setNome(biglietto.getNome());
            dto.setDescrizione(biglietto.getDescrizione());
            dto.setUrlImmagine(biglietto.getUrlImmagine());
            dto.setPrezzo(biglietto.getPrezzo());
            dto.setTipo(biglietto.getTipo());

            dtoList.add(dto);
        }

        return dtoList;
    }
}