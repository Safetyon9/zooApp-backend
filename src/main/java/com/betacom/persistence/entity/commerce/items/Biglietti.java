package com.betacom.persistence.entity.commerce.items;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="biglietti")
public class Biglietti extends Items{

	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipiBiglietti tipo;
}
