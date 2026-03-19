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
@Table(name="scimmie")
public class Scimmie extends Animali{
	
	@Column(nullable = false, length = 30)
	private String tipoMovimenti; // es: "brachiazione", "quadrupede", "saltatoria"
	
	@Column(nullable = false)
	private Boolean codaPrensile; //alcune scimmie usano la coda come una “mano”
	
	@Column(nullable = false, length = 30)
	private String tipoDentatura; // es: "frugivora(frutta)", "onnivora", "insettivora"
	
	
	
	
}