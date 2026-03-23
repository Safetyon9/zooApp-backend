package com.betacom.services.implementations.commerce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.ItemsReq;
import com.betacom.dto.outputs.commerce.ItemsDTO;
import com.betacom.persistence.repository.commerce.IItemsRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.IItemsServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemsImpl implements IItemsServices{
	
	private final IItemsRepository speR;
	private final IMessaggiServices msgS;
	
	@Override
	public void create(ItemsReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(ItemsReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ItemsDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ItemsDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
