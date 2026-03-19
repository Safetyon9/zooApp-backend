package com.betacom.persistence.entity.gestione;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="area")
public class Aree {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column (nullable = false)
	private String tipoStruttura;
	

	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.EAGER
			)
	private List<Animali> animale;
	
	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.EAGER
			)
	private List<Dipendenti> dipendente;
	
    @ManyToOne
    @JoinColumn(name = "turni", referencedColumnName = "id")
    private Turni turno;	
}
