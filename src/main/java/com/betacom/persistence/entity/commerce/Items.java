package com.betacom.persistence.entity.commerce;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table (name="items")
public abstract class Items {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String descrizione;
	
	@Column(name = "url_immagine")
    private String urlImmagine;

    @Column(nullable = false)
    private BigDecimal prezzo;
}
