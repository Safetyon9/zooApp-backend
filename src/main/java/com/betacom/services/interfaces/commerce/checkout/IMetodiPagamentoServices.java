package com.betacom.services.interfaces.commerce.checkout;

import java.util.List;

import com.betacom.dto.inputs.commerce.checkout.MetodiPagamentoReq;
import com.betacom.dto.outputs.commerce.checkout.MetodiPagamentoDTO;

public interface IMetodiPagamentoServices {
	void create(MetodiPagamentoReq req) throws Exception;
	void update(MetodiPagamentoReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<MetodiPagamentoDTO> findAll() throws Exception;
	MetodiPagamentoDTO getById(Integer id) throws Exception;
}
