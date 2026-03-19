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
@Table(name="delfini")
public class Delfini extends Animali{
	
	@Column(name = "comportamento_riproduttivo", nullable = false, length = 30)
	private String comportamentoRiproduttivo;
	
	@Column(name = "velocità_massima", nullable = false)
	private Double velocitaMassima; 
	
	@Column(name = "tipo_dentatura", nullable = false, length = 30)
	private String tipoDentatura;
}