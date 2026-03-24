package com.betacom.services.implementations.commerce.checkout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.items.Items;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.persistence.repository.commerce.checkout.IOggettiOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IOggettiOrdiniServices;
import com.betacom.utilities.Mapper;

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

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(OggettiOrdiniReq req) throws ZooException {
        log.debug("create {}", req);
        
        Ordini o = ordR.findById(req.getOrdineId())
				.orElseThrow(() -> new ZooException("carrello non trovato nel DB: "+ req.getOrdineId()));
		
		Items item = itemRepo.findById(req.getItemId())
		        .orElseThrow(() -> new ZooException("item non trovato nel DB: " + req.getItemId()));

        OggettiOrdini oo = new OggettiOrdini();
        oo.setQuantita(req.getQuantita());
        oo.setPrezzoUnitario(req.getPrezzoUnitario());
        oo.setPrezzoTotale(req.getPrezzoTotale());

        ooR.save(oo);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(OggettiOrdiniReq req) throws ZooException {
        log.debug("update {}", req);

        OggettiOrdini oo = ooR.findById(req.getId())
                .orElseThrow(() -> new ZooException(msgS.get("oggord_ntfnd")));

        oo.setQuantita(req.getQuantita());
        oo.setPrezzoUnitario(req.getPrezzoUnitario());
        oo.setPrezzoTotale(req.getPrezzoTotale());

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