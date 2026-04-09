package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.items.BigliettiReq;
import com.betacom.dto.outputs.commerce.items.BigliettiDTO;

public interface IBigliettiServices {

    void create(BigliettiReq req) throws Exception;
    void update(BigliettiReq req) throws Exception;
    void delete(Integer id) throws Exception;
    BigliettiDTO getById(Integer id) throws Exception;
    List<BigliettiDTO> search(BigliettiReq filtro) throws Exception;
    List<BigliettiDTO> list() throws Exception;
}