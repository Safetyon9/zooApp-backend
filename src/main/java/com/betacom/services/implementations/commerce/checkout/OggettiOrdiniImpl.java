package com.betacom.services.implementations.commerce.checkout;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.repository.commerce.checkout.IOggettiOrdiniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOggettiOrdiniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OggettiOrdiniImpl implements IOggettiOrdiniServices {

    private final IOggettiOrdiniRepository repoOO;
    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(OggettiOrdiniReq req) throws ZooException {
        log.debug("create {}", req);

        OggettiOrdini oo = new OggettiOrdini();
        oo.setQuantita(req.getQuantita());
        oo.setPrezzoUnitario(req.getPrezzoUnitario());
        oo.setPrezzoTotale(req.getPrezzoTotale());

        repoOO.save(oo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(OggettiOrdiniReq req) throws ZooException {
        log.debug("update {}", req);

        OggettiOrdini oo = repoOO.findById(req.getId())
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));

        oo.setQuantita(req.getQuantita());
        oo.setPrezzoUnitario(req.getPrezzoUnitario());
        oo.setPrezzoTotale(req.getPrezzoTotale());

        repoOO.save(oo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        OggettiOrdini oo = repoOO.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));
        repoOO.delete(oo);
    }

    @Override
    public List<OggettiOrdiniDTO> list() {
        log.debug("list oggetti ordini");

        return repoOO.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public OggettiOrdiniDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        OggettiOrdini oo = repoOO.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));
        return toDTO(oo);
    }

    @Override
    public List<OggettiOrdiniDTO> findByOrdineId(Integer ordineId) {
        log.debug("findByOrdineId {}", ordineId);

        return repoOO.findByOrdineId(ordineId).stream()
                .map(this::toDTO)
                .toList();
    }

    private OggettiOrdiniDTO toDTO(OggettiOrdini oo) {
        return OggettiOrdiniDTO.builder()
                .id(oo.getId())
                .itemId(oo.getItem() != null ? oo.getItem().getId() : null)
                .nomeItem(oo.getItem() != null ? oo.getItem().getNome() : null)
                .quantita(oo.getQuantita())
                .prezzoUnitario(oo.getPrezzoUnitario())
                .prezzoTotale(oo.getPrezzoTotale())
                .build();
    }
}