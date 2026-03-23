package com.betacom.persistence.entity.commerce.items;

import java.math.BigDecimal;

import com.betacom.persistence.entity.commerce.Eventi;
import com.betacom.persistence.entity.commerce.Giornate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="biglietto_giornata")
public class BigliettiGiornata {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biglietto_id", nullable = false)
    private Biglietti biglietto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="giornata_id", nullable = false)
    private Giornate giornata;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evento_id", nullable = true)
    private Eventi evento;
	
	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzo;
	
	@Column(nullable = false)
    private Integer stock;
}
