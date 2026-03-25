package com.betacom.services.implementations.commerce.items;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.items.CategorieReq;
import com.betacom.dto.outputs.commerce.items.CategorieDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.items.Categorie;
import com.betacom.persistence.repository.commerce.items.ICategorieRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.ICategorieServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategorieImpl implements ICategorieServices{

    private final ICategorieRepository catR;
    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create(CategorieReq req) throws ZooException {
        log.debug("create {}", req);

        if (req.getNome() == null || req.getNome().isBlank())
            throw new ZooException("Nome categoria non valido");

        Categorie categoria = new Categorie();
        categoria.setNome(req.getNome());

        catR.save(categoria);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(CategorieReq req) throws ZooException {
        log.debug("update {}", req);

        if (req.getId() == null)
            throw new ZooException("ID categoria mancante");

        Categorie categoria = catR.findById(req.getId())
                .orElseThrow(() -> new ZooException("Categoria non trovata"));

        if (req.getNome() != null && !req.getNome().isBlank())
            categoria.setNome(req.getNome());

        catR.save(categoria);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        Categorie categoria = catR.findById(id)
                .orElseThrow(() -> new ZooException("Categoria non trovata"));

        catR.delete(categoria);
    }
    
    @Override
    public List<CategorieDTO> findAll() {
        log.debug("list");

        List<Categorie> lC = catR.findAll();

        return lC.stream()
                .map(c -> Mapper.buildCategorieDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDTO getById(Integer id) throws ZooException {
        log.debug("getById {}", id);

        Categorie categoria = catR.findById(id)
                .orElseThrow(() -> new ZooException("Categoria non trovata"));

        return Mapper.buildCategorieDTO(categoria);
    }
}
