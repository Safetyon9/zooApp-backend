package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;

public interface IPagamentiServices {
	
	void create(PagamentiReq req) throws Exception;
	void update(PagamentiReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<PagamentiDTO> list();
	PagamentiDTO getById(Integer id) throws Exception;
}
