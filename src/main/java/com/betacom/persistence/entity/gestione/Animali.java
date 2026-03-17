package com.betacom.persistence.entity.gestione;

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
@Table (name="animali")
public class Animali {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column(nullable = false)
	private Integer numeroAnimali;
	
	@Column(nullable = false)
	private String specie;
	
    @OneToOne
    @JoinColumn(name = "area", referencedColumnName = "id")
    private Aree area;
    
	@OneToOne
	@JoinColumn(name = "mangime",referencedColumnName ="id")
	private Mangimi mangime;

	
}
