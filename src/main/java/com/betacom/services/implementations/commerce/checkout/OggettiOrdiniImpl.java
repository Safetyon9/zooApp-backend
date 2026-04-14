package com.betacom.services.implementations.commerce.checkout;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Giornate;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.items.Items;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.persistence.repository.commerce.checkout.IOggettiOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOggettiOrdiniServices;
import com.betacom.utilities.Mapper;
import com.betacom.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OggettiOrdiniImpl implements IOggettiOrdiniServices {

    private final IOggettiOrdiniRepository ooR;
    private final IMessaggiServices msgS;
    
    private final IOrdiniRepository ordR;
    private final IItemsRepository itemRepo;
    private final IGiornateRepository giornateR;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(OggettiOrdiniReq req) throws ZooException {
        log.debug("create {}", req);
        
        Ordini o = ordR.findById(req.getOrdineId())
				.orElseThrow(() -> new ZooException("ordine non trovato nel DB: "+ req.getOrdineId()));
		
        OggettiOrdini oo = new OggettiOrdini();
        oo.setQuantita(req.getQuantita());
        oo.setPrezzoUnitario(item.getPrezzo());
        oo.setPrezzoTotale(Utils.calcolaPrezzoTotale(oo.getQuantita(),oo.getPrezzoUnitario()));
        oo.setOrdine(o);
        oo.setItem(item);

        log.debug("ELABORAZIONE RIGA ORDINE: item={}, dataVisita={}", item.getNome(), req.getDataVisita());

        if (req.getDataVisita() != null && !req.getDataVisita().isBlank()) {
            LocalDate dVisita = LocalDate.parse(req.getDataVisita().substring(0, 10)); // Safe parse for YYYY-MM-DD
            Giornate giornata = giornateR.findByData(dVisita)
                    .orElseThrow(() -> new ZooException("Nessuna giornata configurata per la data: " + dVisita));

            if (!Boolean.TRUE.equals(giornata.getAperto()))
                throw new ZooException("Il parco è chiuso nella data selezionata: " + dVisita);

            int quantita = req.getQuantita() != null ? req.getQuantita() : 1;
            if (giornata.getStock() < quantita)
                throw new ZooException("Stock insufficiente per la data " + dVisita + ". Disponibili: " + giornata.getStock());

            giornata.setStock(giornata.getStock() - quantita);
            giornateR.save(giornata);
            oo.setDataVisita(dVisita);
            log.debug("STOCK AGGIORNATO per data {}: nuovo stock={}", dVisita, giornata.getStock());
        }

        ooR.save(oo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(OggettiOrdiniReq req) throws ZooException {
        log.debug("update {}", req);

        OggettiOrdini oo = ooR.findById(req.getId())
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));

        oo.setQuantita(req.getQuantita());

        ooR.save(oo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        OggettiOrdini oo = ooR.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));
        ooR.delete(oo);
    }

    @Override
    public List<OggettiOrdiniDTO> list() {
        log.debug("list oggetti ordini");
        
        List<OggettiOrdini> lB = ooR.findAll();
	    return lB.stream()
	    		.map(b -> Mapper.buildOgettiOrdiniDTO(b))
	    		.collect(Collectors.toList());
    }

    @Override
    public OggettiOrdiniDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        OggettiOrdini oo = ooR.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));
        return Mapper.buildOgettiOrdiniDTO(oo);
    }

    @Override
    public List<OggettiOrdiniDTO> findByOrdineId(Integer ordineId) {
        log.debug("findByOrdineId {}", ordineId);

        return ooR.findByOrdineId(ordineId).stream()
                .map(b -> Mapper.buildOgettiOrdiniDTO(b))
                .toList();
    }

    
}