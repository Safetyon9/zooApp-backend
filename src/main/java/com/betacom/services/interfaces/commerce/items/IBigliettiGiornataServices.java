package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.items.BigliettiGiornateReq;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;

public interface IBigliettiGiornataServices {
	void create(BigliettiGiornateReq req) throws Exception;
	void update(BigliettiGiornateReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<BigliettiGiornateDTO> list();
	BigliettiGiornateDTO getById(Integer id) throws Exception;
}	
