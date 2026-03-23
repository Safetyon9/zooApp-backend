package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.commerce.ClientiDTO;

public interface IClientiServices {
	void create(ClientiReq req) throws Exception;
	void update(ClientiReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<ClientiDTO> findAll() throws Exception;
	ClientiDTO getById(Integer id) throws Exception;

}
