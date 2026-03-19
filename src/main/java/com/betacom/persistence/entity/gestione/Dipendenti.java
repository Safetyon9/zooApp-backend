package com.betacom.persistence.entity.gestione;

import java.util.List;

import com.betacom.persistence.entity.Utenti;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    
	@ManyToOne
	@JoinColumn(name ="area" , referencedColumnName = "id")
	private Aree area;
	
	@OneToOne
	@JoinColumn(name = "utenti",referencedColumnName ="id")
	private Utenti utente;
	
	@ManyToMany
    @JoinTable(
        name = "dipendenti_turni",
        joinColumns = @JoinColumn(name = "dipendente_id"),
        inverseJoinColumns = @JoinColumn(name = "turno_id")
    )
    private List<Turni> turni;
	
}
