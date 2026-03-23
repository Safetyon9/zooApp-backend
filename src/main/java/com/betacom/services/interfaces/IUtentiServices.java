package com.betacom.services.interfaces;

import java.util.List;

import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.exceptions.ZooException;

public interface IUtentiServices {

    void create(UtentiReq req) throws ZooException;
    void update(UtentiReq req) throws ZooException;
    void delete(String userName) throws ZooException;

    List<UtentiDTO> list() throws ZooException;

    UtentiDTO getByUserName(String userName) throws ZooException;
}