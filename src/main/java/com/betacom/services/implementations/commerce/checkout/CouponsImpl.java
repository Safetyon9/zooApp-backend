package com.betacom.services.implementations.commerce.checkout;

import static com.betacom.utilities.Utils.stringToDate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dto.inputs.commerce.checkout.CouponsReq;
import com.betacom.dto.outputs.commerce.checkout.CouponsDTO;
import com.betacom.enums.TipoCoupon;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.repository.commerce.checkout.ICouponsRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.ICouponsServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CouponsImpl implements ICouponsServices{
	
	private final ICouponsRepository couponsRepo;
	private final IMessaggiServices msgS;
	
	@Override
	public void create(CouponsReq req) throws Exception {
		log.debug("create: {}", req);
		
		Coupons c = new Coupons();
		
		c.setCodice(req.getCodice());
		c.setTipo(TipoCoupon.valueOf(req.getTipo()));
		c.setValore(req.getValore());
		c.setAttivo(req.getAttivo());
		c.setDataInizio(stringToDate(req.getDataInizio()));
		c.setDataFine(stringToDate(req.getDataFine()));
		
		couponsRepo.save(c);
		
		
	}

	@Override
	public void update(CouponsReq req) throws Exception {
		Coupons c = couponsRepo.findById(req.getId())
				.orElseThrow(() -> new ZooException("Coupons non trovato nel DB"));
		
		if(req.getCodice() != null)
			c.setCodice(req.getCodice());
		
		if(req.getTipo() != null)
			c.setTipo(TipoCoupon.valueOf(req.getTipo()));
		
		if(req.getValore() != null)
			c.setValore(req.getValore());
		
		if(req.getAttivo() != null)
			c.setAttivo(req.getAttivo());
		
		if(req.getDataInizio() != null)
			c.setDataInizio(stringToDate(req.getDataInizio()));
		
		if(req.getDataFine() != null)
			c.setDataFine(stringToDate(req.getDataFine()));
			
		couponsRepo.save(c);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		Coupons c = couponsRepo.findById(id)
				.orElseThrow(() -> new ZooException("Coupons non trovato nel DB"));
		
		couponsRepo.delete(c);
		
	}

	@Override
	public List<CouponsDTO> findAll() throws Exception {
		List<Coupons> lC = couponsRepo.findAll();
		return lC.stream()
				.map(c -> CouponsDTO.builder()
						.id(c.getId())
	                    .codice(c.getCodice())
	                    .tipo(c.getTipo().toString())
	                    .valore(c.getValore())
	                    .attivo(c.getAttivo())
	                    .dataInizio(c.getDataInizio())
	                    .dataFine(c.getDataFine())
	                    .build())
	            .toList();
	}

	@Override
	public CouponsDTO getById(Integer id) throws Exception {
		
		Coupons c = couponsRepo.findById(id)
				.orElseThrow(() -> new ZooException("Coupons non trovato nel DB"));
		
		return CouponsDTO.builder()
				.id(c.getId())
                .codice(c.getCodice())
                .tipo(c.getTipo().toString())
                .valore(c.getValore())
                .attivo(c.getAttivo())
                .dataInizio(c.getDataInizio())
                .dataFine(c.getDataFine())
                .build();
	}

}
