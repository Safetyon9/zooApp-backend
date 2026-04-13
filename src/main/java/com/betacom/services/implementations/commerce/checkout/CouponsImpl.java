package com.betacom.services.implementations.commerce.checkout;

import static com.betacom.utilities.Utils.stringToDate;

import java.math.BigDecimal;
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
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CouponsImpl implements ICouponsServices {

    private final ICouponsRepository couponsRepo;
    private final IMessaggiServices msgS;

    @Override
    public void create(CouponsReq req) throws Exception {
        log.debug("create: {}", req);

        if (req.getId() != null) {
            throw new ZooException("Coupons non trovato nel DB");
        }

        Coupons c = new Coupons();

        String codiceFinale = req.getCodice();

        if (codiceFinale == null || codiceFinale.isBlank()) {
            codiceFinale = generaCodiceCoupon(req);
        } else if (couponsRepo.existsByCodice(codiceFinale)) {
            throw new ZooException("Codice coupon già esistente");
        }

        c.setCodice(codiceFinale);
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

        if (req.getCodice() != null && !req.getCodice().isBlank()) {
            if (!req.getCodice().equals(c.getCodice()) && couponsRepo.existsByCodice(req.getCodice())) {
                throw new ZooException("Codice coupon già esistente");
            }
            c.setCodice(req.getCodice());
        }

        if (req.getTipo() != null)
            c.setTipo(TipoCoupon.valueOf(req.getTipo()));

        if (req.getValore() != null)
            c.setValore(req.getValore());

        if (req.getAttivo() != null)
            c.setAttivo(req.getAttivo());

        if (req.getDataInizio() != null)
            c.setDataInizio(stringToDate(req.getDataInizio()));

        if (req.getDataFine() != null)
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
                .map(c -> Mapper.buildCouponsDTO(c))
                .toList();
    }

    @Override
    public CouponsDTO getById(Integer id) throws Exception {
        Coupons c = couponsRepo.findById(id)
                .orElseThrow(() -> new ZooException("Coupons non trovato nel DB"));

        return Mapper.buildCouponsDTO(c);
    }

    private String generaCodiceCoupon(CouponsReq req) throws Exception {
        TipoCoupon tipo = TipoCoupon.valueOf(req.getTipo());
        BigDecimal valore = req.getValore();

        if (tipo != TipoCoupon.PERCENTUALE) {
            throw new ZooException("Generazione automatica supportata solo per coupon percentuali");
        }

        if (valore == null) {
            throw new ZooException("Valore coupon obbligatorio per generare il codice");
        }

        int percentuale = valore.intValue();

        if (percentuale <= 0) {
            throw new ZooException("Valore percentuale non valido");
        }

        int progressivo = 1;
        String codice;

        do {
            codice = String.format("ZOO-P%d-%04d", percentuale, progressivo);
            progressivo++;
        } while (couponsRepo.existsByCodice(codice));

        return codice;
    }
}