package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.CouponsReq;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;

public interface ICouponsServices {
	void create(CouponsReq req) throws Exception;
	void update(CouponsReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<CouponsDTO> findAll() throws Exception;
	CouponsDTO getById(Integer id) throws Exception;
	
	
}
