package com.betacom.services.implementations.commerce.checkout;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.checkout.MetodiPagamentoReq;
import com.betacom.dto.outputs.commerce.checkout.MetodiPagamentoDTO;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IMetodiPagamentoServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MetodiPagamentoImpl implements IMetodiPagamentoServices{
	
	private final IMetodiPagamentiRepository metodoRepo;
	private final IMessaggiServices msgS;
	
	
	@Override
	public void create(MetodiPagamentoReq req) throws Exception {
		log.debug("create: {}", req);
		
		MetodiPagamento mp = new MetodiPagamento();
		
		mp.setNome(req.getNome());
		mp.setProvider(req.getProvider());
		
		metodoRepo.save(mp);
		
	}

	@Override
	public void update(MetodiPagamentoReq req) throws Exception {
		MetodiPagamento mp = metodoRepo.findById(req.getId())
				.orElseThrow(() -> new ZooException("Metodo di pagamento non presente nel DB"));
		
		if(req.getNome() != null)
			mp.setNome(req.getNome());
		
		if(req.getProvider() != null)
			mp.setProvider(req.getProvider());
		
		metodoRepo.save(mp);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		MetodiPagamento mp = metodoRepo.findById(id)
				.orElseThrow(() -> new ZooException("Metodo di pagamento non presente nel DB"));
		
		metodoRepo.delete(mp);
	}

	@Override
	public List<MetodiPagamentoDTO> findAll() throws Exception {
		List<MetodiPagamento> lMP = metodoRepo.findAll();
		
		return lMP.stream()
				.map(mp -> MetodiPagamentoDTO.builder()
						.id(mp.getId())
						.nome(mp.getNome())
						.provider(mp.getProvider())
						.build())
				.toList();
	}

	@Override
	public MetodiPagamentoDTO getById(Integer id) throws Exception {
		MetodiPagamento mp = metodoRepo.findById(id)
				.orElseThrow(() -> new ZooException("Metodo di pagamento non presente nel DB"));
		
		return MetodiPagamentoDTO.builder()
				.id(mp.getId())
				.nome(mp.getNome())
				.provider(mp.getProvider())
				.build();
	}

}
