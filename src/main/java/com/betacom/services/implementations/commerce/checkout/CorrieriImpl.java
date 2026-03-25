package com.betacom.services.implementations.commerce.checkout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.CorrieriReq;
import com.betacom.dto.inputs.commerce.items.CategorieReq;
import com.betacom.dto.outputs.commerce.checkout.CorrieriDTO;
import com.betacom.dto.outputs.commerce.items.CategorieDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Corrieri;
import com.betacom.persistence.repository.commerce.checkout.ICorrieriRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.ICorrieriServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CorrieriImpl implements ICorrieriServices{


    private final ICorrieriRepository corR;
    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(CorrieriReq req) throws Exception {
        log.debug("create {}", req);

        if (req.getNome() == null || req.getNome().isBlank())
            throw new ZooException("Nome corriere non valido");

        Corrieri c = new Corrieri();
        c.setNome(req.getNome());

        corR.save(c);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(CorrieriReq req) throws Exception {
        log.debug("update {}", req);

        if (req.getId() == null)
            throw new ZooException("ID corriere mancante");

        Corrieri c = corR.findById(req.getId())
                .orElseThrow(() -> new ZooException("Corriere non trovato"));

        if (req.getNome() != null && !req.getNome().isBlank())
            c.setNome(req.getNome());

        corR.save(c);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);

        Corrieri c = corR.findById(id)
                .orElseThrow(() -> new ZooException("Corriere non trovato"));

        corR.delete(c);
    }

    @Override
    public List<CorrieriDTO> findAll() {
        log.debug("list");

        return corR.findAll().stream()
                .map(c -> Mapper.buildCorrieriDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public CorrieriDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        Corrieri c = corR.findById(id)
                .orElseThrow(() -> new ZooException("Corriere non trovato"));

        return Mapper.buildCorrieriDTO(c);
    }

}
