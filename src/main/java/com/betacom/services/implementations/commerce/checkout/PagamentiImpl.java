package com.betacom.services.implementations.commerce.checkout;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.persistence.repository.commerce.checkout.IPagamentiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IPagamentiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagamentiImpl implements IPagamentiServices{
	
	private final IPagamentiRepository pagaR;
	private final IMessaggiServices msgS;
	
	@Override
	public void create(PagamentiReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(PagamentiReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<PagamentiDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PagamentiDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
