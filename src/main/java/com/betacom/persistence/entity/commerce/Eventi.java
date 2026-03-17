package com.betacom.persistence.entity.commerce;

import java.time.LocalDate;
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
@Table (name="eventi")
public class Eventi {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
	private String tipoEvento;
	
	@Column(nullable = false)
	private LocalDate dataEvento;
	
	@OneToMany
	@JoinColumn(name ="giornate" , referencedColumnName = "id")
	private List<Giornate> giornata;
}