package com.betacom.services.implementations.commerce.items;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.items.BigliettiGiornateReq;
import com.betacom.dto.outputs.commerce.items.BigliettiGiornateDTO;
import com.betacom.persistence.repository.commerce.IEventiRepository;
import com.betacom.persistence.repository.commerce.IGiornateRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiGiornataRepository;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.items.IBigliettiGiornataServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BigliettiGiornataImpl implements IBigliettiGiornataServices{

	private final IBigliettiGiornataRepository speR;
	private final IMessaggiServices msgS;
	
	private final IBigliettiRepository bigR;
	private final IGiornateRepository gioR;
	private final IEventiRepository eveR;
	
	@Override
	public void create(BigliettiGiornateReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(BigliettiGiornateReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<BigliettiGiornateDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BigliettiGiornateDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
