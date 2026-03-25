package com.betacom.persistence.entity.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.betacom.enums.StatoSpedizione;

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spedizioni")
public class Spedizioni {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private String indirizzo;

    @Column(nullable = true, unique = true)
    private String trackingNumber;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoSpedizione stato = StatoSpedizione.ATTESA;
    
    @Column(nullable = false)
    private LocalDate dataCreazione = LocalDate.now();
    
    @Column(nullable = true)
    private LocalDate dataAggiornamento;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordine_id")
    private Ordini ordine;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corriere_id")
    private Corrieri corriere;
}
