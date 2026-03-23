package com.betacom.services.interfaces.commerce.items;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;

import java.util.List;

import com.betacom.dto.outputs.commerce.items.BigliettiDTO;

public interface IBigliettiServices {

    void create(BigliettiReq req) throws Exception;
    void update(BigliettiReq req) throws Exception;
    void delete(Integer id) throws Exception;
    BigliettiDTO getById(Integer id) throws Exception;
    List<BigliettiDTO> findAll() throws Exception;
}
//create void o con id
//create update req tutti boid