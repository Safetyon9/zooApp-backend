package com.betacom.services.interfaces.commerce.items;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;

import java.util.List;

import com.betacom.dto.outputs.commerce.items.BigliettiDTO;

public interface IBigliettiServices {

    BigliettiDTO create(BigliettiReq req) throws Exception;
    BigliettiDTO update(Integer id, BigliettiReq req) throws Exception;
    void delete(Integer id) throws Exception;
    BigliettiDTO getById(Integer id) throws Exception;
    List<BigliettiDTO> getAll() throws Exception;
}