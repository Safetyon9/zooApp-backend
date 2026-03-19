package com.betacom.persistence.entity.commerce;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name="biglietti")
public class Biglietti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
	private LocalDate dataBiglietto;
	
	@Column(nullable = false)
	private Integer prezzoBiglietto;
	
	@OneToOne
	@JoinColumn(name = "prodotti",referencedColumnName ="id")
	private Prodotti prodotto;

	@ManyToOne
	@JoinColumn(name ="eventi" , referencedColumnName = "id")
	private Eventi evento;
}