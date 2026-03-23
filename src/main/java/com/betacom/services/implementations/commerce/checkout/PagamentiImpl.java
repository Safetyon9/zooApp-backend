package com.betacom.services.implementations.commerce.checkout;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.commerce.checkout.PagamentiReq;
import com.betacom.dto.outputs.commerce.checkout.PagamentiDTO;
import com.betacom.enums.StatoPagamento;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.commerce.checkout.Coupons;
import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;
import com.betacom.persistence.entity.commerce.checkout.Ordini;
import com.betacom.persistence.entity.commerce.checkout.Pagamenti;
import com.betacom.persistence.repository.commerce.checkout.ICouponsRepository;
import com.betacom.persistence.repository.commerce.checkout.IMetodiPagamentiRepository;
import com.betacom.persistence.repository.commerce.checkout.IOrdiniRepository;
import com.betacom.persistence.repository.commerce.checkout.IPagamentiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.commerce.checkout.IPagamentiServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagamentiImpl implements IPagamentiServices{
	
	private final IPagamentiRepository pagaR;
	private final IMessaggiServices msgS;
	
	private final IOrdiniRepository ordR;
	private final IMetodiPagamentiRepository metR;
	private final ICouponsRepository couR;
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void create(PagamentiReq req) throws ZooException {
		log.debug("create {}", req);
		
		if (req.getImporto() == null)
			throw new ZooException("Importo non trovato.");
		if (req.getOrdineId() == null)
			throw new ZooException("Ordine collegato non trovato.");
		if (req.getMetodoPagamentoId() == null)
			throw new ZooException("Metodo di pagamento collegato non trovato.");
		if (req.getCouponId() == null)
			throw new ZooException("Coupon collegato non trovato.");
		
		
		Ordini ordine = ordR.findById(req.getOrdineId())
	            .orElseThrow(() -> new ZooException("Ordine non trovato nel DB"));
		MetodiPagamento metodo = metR.findById(req.getMetodoPagamentoId())
	            .orElseThrow(() -> new ZooException("Ordine non trovato nel DB"));
		Coupons coupon = couR.findById(req.getCouponId())
	            .orElseThrow(() -> new ZooException("Ordine non trovato nel DB"));
	          
	            
		
		Pagamenti pag = new Pagamenti();
		pag.setImporto(req.getImporto());
		pag.setOrdine(ordine);
		pag.setMetodoPagamento(metodo);
		pag.setCoupon(coupon);
		
		pagaR.save(pag);
		
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void update(PagamentiReq req) throws ZooException {
		log.debug("update {}", req);
		
		Optional<Pagamenti> pag = pagaR.findById(req.getId());
		if (pag.isEmpty())
			throw new ZooException("Pagamento non trovato in DB");
		
		Pagamenti p = pag.get();

		if (req.getDataEsecuzione() != null)
			p.setDataEsecuzione(req.getDataEsecuzione());
		if (req.getStato() != null)
			p.setStato(StatoPagamento.valueOf(req.getStato().toUpperCase()));
		
		pagaR.save(p);
	}
	
	@Transactional (rollbackFor = ZooException.class)
	@Override
	public void delete(Integer id) throws ZooException {
		log.debug("delete {}", id);
		
		Optional<Pagamenti> pag = pagaR.findById(id);
		if (pag.isEmpty())
			throw new ZooException("Pagamento non trovato in DB");

		pagaR.delete(pag.get());
	}
	
	@Override
	public List<PagamentiDTO> list() {
		log.debug("list");
		List<Pagamenti> lP = pagaR.findAll();

		return lP.stream()
	    		.map(p -> Mapper.buildPagamentoDTO(p))
	    		.collect(Collectors.toList());
	}
	
	@Override
	public PagamentiDTO getById(Integer id) throws Exception {
		log.debug("list by id, {}", id);
		
		Optional<Pagamenti> pag = pagaR.findById(id);
		if (pag.isEmpty())
			throw new ZooException("Pagamento non trovato in DB");

		return Mapper.buildPagamentoDTO(pag.get());
	}

}
