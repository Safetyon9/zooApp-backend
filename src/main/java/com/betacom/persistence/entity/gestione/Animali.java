package com.betacom.persistence.entity.gestione;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table (name="animali")
public class Animali {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column(nullable = false)
	private Double peso;
	
	@Column(nullable = false)
    private Double altezza;
	
	@Column(nullable = false, length = 50)
	private String colore;
	
	@Column(nullable = false, length = 20)
	private String sesso;
	
	@Column(nullable = false, length = 50)
	private String dieta;
	
	@Column(nullable = false, length = 50)
    private String specie;
	
	@Column(nullable = false, length = 50)
    private String provenienza;
	
	@Column(nullable = false, length = 50)
    private String habitat;
	
	@Column(nullable = false)
	private boolean pericolosita;
	
	@Column(nullable = false)
	private Integer eta;
	
	@Column(nullable = false)
	private Integer aspettativaVita;
	
    @OneToOne
    @JoinColumn(name = "area", referencedColumnName = "id")
    private Aree area;
    
	@OneToOne
	@JoinColumn(name = "mangime",referencedColumnName ="id")
	private Mangimi mangime;

	
}
