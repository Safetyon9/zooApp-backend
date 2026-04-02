package com.betacom.services.interfaces.commerce;

import java.util.List;

import com.betacom.dto.inputs.commerce.GiornateReq;
import com.betacom.dto.outputs.commerce.GiornateDTO;

public interface IGiornateServices {

    void create(GiornateReq req) throws Exception;
    void update(GiornateReq req) throws Exception;
    void delete(Integer id) throws Exception;
    GiornateDTO getById(Integer id) throws Exception;
    List<GiornateDTO> findAll() throws Exception;
}