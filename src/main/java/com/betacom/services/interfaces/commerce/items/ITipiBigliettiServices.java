package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.items.TipiBigliettiReq;
import com.betacom.dto.outputs.commerce.items.TipiBigliettiDTO;

public interface ITipiBigliettiServices {
    
    void create(TipiBigliettiReq req) throws Exception;

    void update(TipiBigliettiReq req) throws Exception;

    void delete(Integer id) throws Exception;

    List<TipiBigliettiDTO> findAll() throws Exception;

    TipiBigliettiDTO getById(Integer id) throws Exception;
}