package com.betacom.persistence.entity.gestione.animali;

import com.betacom.persistence.entity.gestione.Animali;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="lemuri")
public class Lemuri extends Animali{
	
	@Column(name = "tipo_movimento", nullable = false, length = 30)
	private String tipoMovimento;
	
	@Column(name = "lunghezza_coda", nullable = false)
	private Double lunghezzaCoda;
	
	@Column(name = "attività_diurna", nullable = false, length = 30)
	private String attivitaDiurna;
}
