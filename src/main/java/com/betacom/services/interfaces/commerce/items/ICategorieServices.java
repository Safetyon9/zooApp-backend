package com.betacom.services.interfaces.commerce.items;

import java.util.List;

import com.betacom.dto.inputs.commerce.items.CategorieReq;
import com.betacom.dto.outputs.commerce.items.CategorieDTO;

public interface ICategorieServices {
	void create(CategorieReq req) throws Exception;
	void update(CategorieReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<CategorieDTO> findAll() throws Exception;
	CategorieDTO getById(Integer id) throws Exception;
}
