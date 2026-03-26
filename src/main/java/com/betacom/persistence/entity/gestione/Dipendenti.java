package com.betacom.persistence.entity.gestione;

import java.util.List;

import com.betacom.persistence.entity.Utenti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="dipendenti")
public class Dipendenti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String cognome;
    
    @Column(name = "codice_fiscale", nullable=false, unique = true)
    private String codiceFiscale;
	
	@Column
    private String ruolo;
	
	@OneToOne(
			optional = false,
			fetch = FetchType.LAZY
			)
	@JoinColumn(name = "utente_user_name", referencedColumnName = "userName", nullable = false, unique = true)
    private Utenti utente;
    
	@OneToMany(mappedBy = "dipendente")
    private List<MovimentiMangimi> movimentiMangime;
	
	@OneToMany(mappedBy = "dipendente")
    private List<AssegnazioneTurni> assegnazioniTurni;
	
}
