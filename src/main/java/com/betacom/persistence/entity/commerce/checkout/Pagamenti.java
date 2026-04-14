package com.betacom.persistence.entity.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.betacom.enums.StatoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamenti")
public class Pagamenti {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importo;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPagamento stato = StatoPagamento.ATTESA;
	
	@Column(nullable = false)
    private LocalDateTime dataCreazione = LocalDateTime.now();
	
	@Column(nullable = true)
    private LocalDateTime dataEsecuzione;
	
	@OneToOne(
			fetch = FetchType.LAZY,
			optional = false
			)
    @JoinColumn(name = "ordine_id")
    private Ordini ordine;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metodo_pagamento_id")
    private MetodiPagamento metodoPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = true)
    private Coupons coupon;
}
