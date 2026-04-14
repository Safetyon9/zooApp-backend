package com.betacom.services.interfaces.commerce;

import java.util.List;

import com.betacom.dto.inputs.commerce.OggettiCarrelliReq;
import com.betacom.dto.outputs.commerce.OggettiCarrelliDTO;

public interface IOggettiCarrelliServices {
	Integer create(OggettiCarrelliReq req) throws Exception;
	void update(OggettiCarrelliReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<OggettiCarrelliDTO> findAll() throws Exception;
	OggettiCarrelliDTO getById(Integer id) throws Exception;
}
