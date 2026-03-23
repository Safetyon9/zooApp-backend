package com.betacom.services.implementations.commerce.checkout;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;
import com.betacom.enums.StatoOrdine;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOrdiniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdiniImpl implements IOrdiniServices {

    private final IOrdiniRepository repoO;
    private final IClientiRepository repoC;
    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(OrdiniReq req) throws ZooException {
        log.debug("create {}", req);

        Clienti cliente = repoC.findById(req.getClienteId())
                .orElseThrow(() -> new ZooException(msgS.get("cli_ntfnd")));

        Ordini o = new Ordini();
        o.setCliente(cliente);
        o.setNome(cliente.getNome());
        o.setCognome(cliente.getCognome());
        o.setIndirizzo(req.getIndirizzo() != null ? req.getIndirizzo() : cliente.getIndirizzo());
        o.setDataOrdine(LocalDateTime.now());
        o.setStato(StatoOrdine.ORDINATO);

        // se vuoi creare anche le righe qui, itera su req.getOggetti() e popola la lista
        // o.setOggettiOrdine(...)

        repoO.save(o);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(OrdiniReq req) throws ZooException {
        log.debug("update {}", req);

        Ordini o = repoO.findById(req.getId())
                .orElseThrow(() -> new ZooException(msgS.get("ord_ntfnd")));

        if (req.getIndirizzo() != null) {
            o.setIndirizzo(req.getIndirizzo());
        }

        repoO.save(o);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void updateStato(Integer id, StatoOrdine stato) throws ZooException {
        log.debug("updateStato {} -> {}", id, stato);

        Ordini o = repoO.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("ord_ntfnd")));

        o.setStato(stato);
        repoO.save(o);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        Ordini o = repoO.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("ord_ntfnd")));

        repoO.delete(o);
    }

    @Override
    public List<OrdiniDTO> list() {
        log.debug("list ordini");

        return repoO.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public OrdiniDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        Ordini o = repoO.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("ord_ntfnd")));
        return toDTO(o);
    }

    @Override
    public List<OrdiniDTO> findByClienteId(Integer clienteId) {
        log.debug("findByClienteId {}", clienteId);

        return repoO.findByClienteId(clienteId).stream()
                .map(this::toDTO)
                .toList();
    }

    private OrdiniDTO toDTO(Ordini o) {
        List<OggettiOrdiniDTO> righe = o.getOggettiOrdine().stream()
                .map(oo -> OggettiOrdiniDTO.builder()
                        .id(oo.getId())
                        .itemId(oo.getItem().getId())
                        .nomeItem(oo.getItem().getNome())
                        .quantita(oo.getQuantita())
                        .prezzoUnitario(oo.getPrezzoUnitario())
                        .prezzoTotale(oo.getPrezzoTotale())
                        .build())
                .toList();

        return OrdiniDTO.builder()
                .id(o.getId())
                .clienteId(o.getCliente().getId())
                .nome(o.getNome())
                .cognome(o.getCognome())
                .indirizzo(o.getIndirizzo())
                .dataOrdine(o.getDataOrdine())
                .stato(o.getStato())
                .righe(righe)
                .build();
    }
}