package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.OggettiOrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OggettiOrdiniDTO;
import com.betacom.exceptions.ZooException;

public interface IOggettiOrdiniServices {

    void create(OggettiOrdiniReq req) throws ZooException;
    void update(OggettiOrdiniReq req) throws ZooException;
    void delete(Integer id) throws ZooException;

    List<OggettiOrdiniDTO> list() throws ZooException;

    OggettiOrdiniDTO getById(Integer id) throws ZooException;

    List<OggettiOrdiniDTO> findByOrdineId(Integer ordineId) throws ZooException;
}