package com.betacom.services.interfaces.commerce;


import java.util.List;

import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.dto.outputs.commerce.EventiDTO;


public interface IEventiServices{
	void create(EventiReq req) throws Exception;
	void update(EventiReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<EventiDTO> find(EventiReq req) throws Exception;
	List<EventiDTO> findAll() throws Exception;
	EventiDTO getById(Integer id) throws Exception;
	
	

}

