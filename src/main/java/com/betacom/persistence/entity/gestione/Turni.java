package com.betacom.persistence.entity.gestione;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="turni")

public class Turni {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column (nullable =false)
	private LocalTime inizioTurno;
	
	@Column (nullable =false)
	private LocalTime fineTurno;
	
    @OneToMany
    @JoinColumn(name = "area", referencedColumnName = "id")
    private List<Aree> area;
    
    @OneToMany
    @JoinColumn(name = "dipendenti", referencedColumnName = "id")
    private List<Dipendenti> dipendente;
	
}