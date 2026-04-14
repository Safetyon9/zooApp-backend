package com.betacom.persistence.entity.commerce;

import java.time.LocalDate;
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
@Table (name="giornate_zoo")
public class Giornate {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private LocalDate data;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evento_id", nullable = true)
    private Eventi evento;
	
	@Column(nullable = false)
    private Boolean aperto;
	
	@Column(nullable = false)
    private Integer stock;
}