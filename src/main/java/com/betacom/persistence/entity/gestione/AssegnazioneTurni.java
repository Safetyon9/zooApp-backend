package com.betacom.persistence.entity.gestione;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "assegnazioni_turni")
public class AssegnazioneTurni {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private LocalDate data;
	
	@ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendenti dipendente;

    @ManyToOne
    @JoinColumn(name = "turno_id", nullable = false)
    private Turni turno;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Aree area;
}
