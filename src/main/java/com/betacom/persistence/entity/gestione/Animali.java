package com.betacom.persistence.entity.gestione;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table (name="animali")
public abstract class Animali {
	
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
	
	@Column(name = "aspettative_vita", nullable = false)
	private Integer aspettativaVita;
	
	@Column(name = "date_arrivo", nullable = false)
	private LocalDate dataArrivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Aree area;
    
	@ManyToMany(mappedBy = "animali")
    private List<Mangimi> Mangimi;
}
