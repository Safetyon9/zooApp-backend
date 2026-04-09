package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.items.ProdottiReq;
import com.betacom.dto.outputs.commerce.items.ProdottiDTO;
import com.betacom.exceptions.ZooException;

public interface IProdottiServices {

    void create(ProdottiReq req) throws ZooException;
    void update(ProdottiReq req) throws ZooException;
    void delete(Integer id) throws ZooException;

    List<ProdottiDTO> list() throws ZooException;

    ProdottiDTO getBySku(Long sku) throws ZooException;

    List<ProdottiDTO> search(ProdottiReq req) throws ZooException;
	
}