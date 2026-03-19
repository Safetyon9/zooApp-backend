package com.betacom.persistence.entity.gestione;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="aree")
public class Aree {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column (nullable = false)
	private String tipoStruttura;
	
	@Column(nullable = false)
    private String nomeArea;
	
	@Column
    private String descrizione;

    @Column(nullable = false)
    private Integer capienza;

	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.LAZY
			)
	private List<Animali> animale;
	
	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.LAZY
			)
	private List<MangimiStocks> mangimiStocks;
	
	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.LAZY
			)
    private List<AssegnazioneTurni> assegnazioniTurno;
}
