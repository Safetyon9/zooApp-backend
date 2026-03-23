package com.betacom.persistence.entity;

import java.time.LocalDate;

import com.betacom.enums.Roles;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.entity.gestione.Dipendenti;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (
		name="utenti",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "dipendente_id"),
	        @UniqueConstraint(columnNames = "cliente_id")
	    	}
		)
public class Utenti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, unique=true)
	private String userName;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false, unique=true)
	private String email;
	
	@Column(nullable=false)
    private Boolean isActive = true;
	
	@Column(nullable=false)
	private LocalDate dataIscrizione = LocalDate.now();
	
	@Enumerated(EnumType.STRING)
    @Column(nullable=false)
	private Roles role;
	
	@OneToOne(
			optional = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
    @JoinColumn(name="dipendente_id", referencedColumnName="id", unique = true)
    private Dipendenti dipendente;
	
	@OneToOne(
			optional = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
    @JoinColumn(name="cliente_id", referencedColumnName="id", unique = true)
    private Clienti cliente;
	
	
}