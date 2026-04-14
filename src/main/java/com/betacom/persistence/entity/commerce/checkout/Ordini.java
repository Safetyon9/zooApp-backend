package com.betacom.persistence.entity.commerce.checkout;

import java.time.LocalDateTime;
import java.util.List;

import com.betacom.enums.StatoOrdine;
import com.betacom.persistence.entity.commerce.Clienti;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordine")
public class Ordini {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column (nullable = false)
	private String indirizzo;
	
	@Column (nullable = false)
	private String nome;
	
	@Column (nullable = false)
	private String cognome;
	
	@Column(nullable = false)
    private LocalDateTime dataOrdine = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = true)
    private Clienti cliente;
	
	@OneToMany(
			mappedBy = "ordine",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
			)
    private List<OggettiOrdini> oggettiOrdine;
	
	@OneToOne(
			fetch = FetchType.LAZY
			)
    @JoinColumn(name = "pagamenti_id")
    private Pagamenti pagamenti;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoOrdine stato = StatoOrdine.ORDINATO;
	
}