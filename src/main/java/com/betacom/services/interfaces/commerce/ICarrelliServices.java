package com.betacom.services.interfaces.commerce;

import java.util.List;

import com.betacom.dto.inputs.commerce.CarrelliReq;
import com.betacom.dto.outputs.commerce.CarrelliDTO;

public interface ICarrelliServices {
	void create(CarrelliReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<CarrelliDTO> findAll() throws Exception;
	CarrelliDTO getById(Integer id) throws Exception;
}
