package com.betacom.services.interfaces.commerce;

import java.util.List;

import com.betacom.dto.inputs.commerce.ItemsReq;
import com.betacom.dto.outputs.commerce.ItemsDTO;

public interface IItemsServices {
	void create(ItemsReq req) throws Exception;
	void update(ItemsReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<ItemsDTO> list();
	ItemsDTO getById(Integer id) throws Exception;
}
