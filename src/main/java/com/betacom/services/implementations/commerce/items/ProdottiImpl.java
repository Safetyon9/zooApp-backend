package com.betacom.services.implementations.commerce.items;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.ProdottiReq;
import com.betacom.dto.outputs.commerce.items.ProdottiDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.items.Categorie;
import com.betacom.persistence.entity.commerce.items.Prodotti;
import com.betacom.persistence.repository.commerce.items.ICategorieRepository;
import com.betacom.persistence.repository.commerce.items.IProdottiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IProdottiServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdottiImpl implements IProdottiServices {

    private final IProdottiRepository repoP;
    private final IMessaggiServices msgS;
    private final ICategorieRepository catR;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(ProdottiReq req) {
        log.debug("create {}", req);
        
        Categorie categoria = catR.findById(req.getCategoriaId())
                .orElseThrow(() -> new ZooException(msgS.get("item_ntfnd")));

        if (repoP.findBySku(req.getSku()).isPresent()) {
            throw new ZooException(msgS.get("prd_exists"));
        }

        Prodotti p = new Prodotti();
        p.setNome(req.getNome());
        p.setDescrizione(req.getDescrizione());
        p.setUrlImmagine(req.getUrlImmagine());
        p.setPrezzo(req.getPrezzo());

        p.setDimensioni(req.getDimensioni());
        p.setPeso(req.getPeso());
        p.setStock(req.getStock());
        p.setSku(req.getSku());
        p.setCategoria(categoria);

        repoP.save(p);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(ProdottiReq req) throws ZooException {
        log.debug("update {}", req);

        Prodotti p = repoP.findById(req.getItemId())
                .orElseThrow(() -> new ZooException(msgS.get("prd_ntfnd")));

        
        if (req.getNome() != null) p.setNome(req.getNome());
        if (req.getDescrizione() != null) p.setDescrizione(req.getDescrizione());
        if (req.getUrlImmagine() != null) p.setUrlImmagine(req.getUrlImmagine());
        if (req.getPrezzo() != null) p.setPrezzo(req.getPrezzo());

        if (req.getDimensioni() != null) p.setDimensioni(req.getDimensioni());
        if (req.getPeso() != null) p.setPeso(req.getPeso());
        if (req.getStock() != null) p.setStock(req.getStock());
        if (req.getSku() != null) p.setSku(req.getSku());
        
        if (req.getCategoriaId() != null) {
            Categorie categoria = catR.findById(req.getCategoriaId())
                    .orElseThrow(() -> new ZooException(msgS.get("item_ntfnd")));
            p.setCategoria(categoria);
        }

        repoP.save(p);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete id={}", id);

        Prodotti p = repoP.findById(id)
                .orElseThrow(() -> new ZooException(msgS.get("prd_ntfnd")));
        p.setDeleted(true);
    }

    @Override
    public List<ProdottiDTO> list() {
        log.debug("list prodotti");

        return repoP.findAll().stream()
                .map(p -> Mapper.buildProdottiDTO(p))
                .toList();
    }

    @Override
    public ProdottiDTO getBySku(Long sku) throws ZooException {
        log.debug("getBySku {}", sku);

        Prodotti p = repoP.findBySku(sku)
                .orElseThrow(() -> new ZooException(msgS.get("prd_ntfnd")));

        return Mapper.buildProdottiDTO(p);
    }

    @Override
    public List<ProdottiDTO> find(Integer id, String nome, String descrizione,
                                  String categoria, Integer stock) {

        log.debug("find {} / {} / {} / {} / {}", id, nome, descrizione, categoria, stock);
        return list(); 
    }
}