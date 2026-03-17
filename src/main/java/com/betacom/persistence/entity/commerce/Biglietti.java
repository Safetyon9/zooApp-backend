package com.betacom.persistence.entity.commerce;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Integer prezzo;
	
	
	
}
