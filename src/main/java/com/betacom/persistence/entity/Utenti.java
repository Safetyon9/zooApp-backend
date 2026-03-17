package com.betacom.persistence.entity;

import com.betacom.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="utenti")
public class Utenti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private Roles role;
	
	@OneToOne
	@JoinColumn(name = "dipendenti",referencedColumnName ="id")
	private Dipendenti dipendente;
	
	
	
	
}