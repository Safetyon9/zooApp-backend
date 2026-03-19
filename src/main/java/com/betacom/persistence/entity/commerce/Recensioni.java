package com.betacom.persistence.entity.commerce;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="recensione")
public class Recensioni {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	
	@Column (nullable = false)
	private Integer voto; // 1-5 
	
	
	@Column (nullable = false, length = 500)
	private String testo;
	
	@Column (nullable = false, length = 50)
	private String titolo;
	
	@Column (nullable = false)
	private LocalDateTime dataCreazione;

	@ManyToOne
    @JoinColumn(name = "cliente")
    private Clienti cliente;
	
	
	
}