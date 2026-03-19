package com.betacom.persistence.entity.commerce;

import java.util.List;

import com.betacom.persistence.entity.Utenti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name="clienti")
public class Clienti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column (nullable =false)
	private String email;
	
	@Column (nullable =false)
	private String nome;
	
	@Column (nullable =false)
	private String cognome;
	
	@Column (nullable =false)
	private String indirizzo;	
	
	@OneToOne
	@JoinColumn(name = "utenti",referencedColumnName ="id", unique = true)
	private Utenti utente;
	
	@OneToOne
	@JoinColumn(name = "carrelli",referencedColumnName ="id", unique = true)
	private Carrelli carrello;
	
	@OneToMany(mappedBy = "cliente")
	private List<Ordini> ordini;
}
