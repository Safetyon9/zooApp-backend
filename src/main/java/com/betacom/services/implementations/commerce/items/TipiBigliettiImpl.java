package com.betacom.services.implementations.commerce.items;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.TipiBigliettiReq;
import com.betacom.dto.outputs.commerce.items.TipiBigliettiDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.items.TipiBiglietti;
import com.betacom.persistence.repository.commerce.items.ITipiBigliettiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.ITipiBigliettiServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TipiBigliettiImpl implements ITipiBigliettiServices {

    private final ITipiBigliettiRepository tipiR;
    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(TipiBigliettiReq req) throws ZooException {
        log.debug("create {}", req);

        if (req.getNome() == null || req.getNome().isBlank())
            throw new ZooException("Nome tipo biglietto non valido");

        TipiBiglietti tipo = new TipiBiglietti();
        tipo.setNome(req.getNome());

        tipiR.save(tipo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(TipiBigliettiReq req) throws ZooException {
        log.debug("update {}", req);

        if (req.getId() == null)
            throw new ZooException("ID tipo biglietto mancante");

        TipiBiglietti tipo = tipiR.findById(req.getId())
                .orElseThrow(() -> new ZooException("Tipo biglietto non trovato"));

        if (req.getNome() != null && !req.getNome().isBlank())
            tipo.setNome(req.getNome());

        tipiR.save(tipo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        TipiBiglietti tipo = tipiR.findById(id)
                .orElseThrow(() -> new ZooException("Tipo biglietto non trovato"));

        tipiR.delete(tipo);
    }

    @Override
    public List<TipiBigliettiDTO> findAll() {
        log.debug("list");

        List<TipiBiglietti> lT = tipiR.findAll();

        return lT.stream()
                .map(t -> Mapper.buildTipiBigliettiDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public TipiBigliettiDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        TipiBiglietti tipo = tipiR.findById(id)
                .orElseThrow(() -> new ZooException("Tipo biglietto non trovato"));

        return Mapper.buildTipiBigliettiDTO(tipo);
    }
}