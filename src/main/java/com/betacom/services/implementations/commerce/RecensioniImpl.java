package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.RecensioniReq;
import com.betacom.dto.outputs.commerce.RecensioniDTO;
import com.betacom.persistence.repository.commerce.IRecensioniRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.IRecensioniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecensioniImpl implements IRecensioniServices{
	
	private final IRecensioniRepository speR;
	private final IMessaggiServices msgS;
	
	@Override
	public void create(RecensioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(RecensioniReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<RecensioniDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public RecensioniDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
