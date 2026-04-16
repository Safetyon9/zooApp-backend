package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.SpedizioniReq;
import com.betacom.dto.outputs.commerce.checkout.SpedizioniDTO;

public interface ISpedizioniServices {
	void create(SpedizioniReq req) throws Exception;
	void update(SpedizioniReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<SpedizioniDTO> list();
	SpedizioniDTO getById(Integer id) throws Exception;
	SpedizioniDTO getByOrdineId(Integer id) throws Exception;
}
