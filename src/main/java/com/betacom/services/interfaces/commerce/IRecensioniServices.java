package com.betacom.services.interfaces.commerce;

import java.util.List;

import com.betacom.dto.inputs.commerce.RecensioniReq;
import com.betacom.dto.outputs.commerce.RecensioniDTO;

public interface IRecensioniServices {
	void create(RecensioniReq req) throws Exception;
	void update(RecensioniReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<RecensioniDTO> list();
	RecensioniDTO getById(Integer id) throws Exception;
}
