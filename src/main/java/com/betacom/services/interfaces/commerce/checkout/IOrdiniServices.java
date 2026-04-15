package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.OrdiniReq;
import com.betacom.dto.outputs.commerce.checkout.OrdiniDTO;
import com.betacom.enums.StatoOrdine;
import com.betacom.exceptions.ZooException;

public interface IOrdiniServices {

    Integer create(OrdiniReq req) throws ZooException;
    void update(OrdiniReq req) throws ZooException;
    void updateStato(Integer id, StatoOrdine stato) throws ZooException;
    void delete(Integer id) throws ZooException;

    List<OrdiniDTO> list() throws ZooException;
    List<OrdiniDTO> listByClienteId(Integer clienteId);

    OrdiniDTO getById(Integer id) throws ZooException;

    List<OrdiniDTO> findByClienteId(Integer clienteId) throws ZooException;
}