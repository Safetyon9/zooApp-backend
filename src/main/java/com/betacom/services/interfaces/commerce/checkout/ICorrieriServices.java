package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.CorrieriReq;
import com.betacom.dto.outputs.commerce.checkout.CorrieriDTO;

public interface ICorrieriServices {
	void create(CorrieriReq req) throws Exception;
	void update(CorrieriReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<CorrieriDTO> findAll() throws Exception;
	CorrieriDTO getById(Integer id) throws Exception;
}
