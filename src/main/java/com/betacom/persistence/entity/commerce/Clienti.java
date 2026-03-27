package com.betacom.persistence.entity.commerce;

import java.util.List;

import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.checkout.Ordini;

import jakarta.persistence.CascadeType;
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
@Table (name="clienti")
public class Clienti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column (nullable =false)
	private String nome;
	
	@Column (nullable =false)
	private String cognome;
	
	@Column (nullable =false)
	private String indirizzo;	
	
	@Column (nullable = false)
	private String comune;
	
	@Column (nullable = false)
	private String cap;
	
	@Column (nullable = false,
			length = 20)
	private String telefono;
	
	@OneToOne
    @JoinColumn(name = "utente_user_name", referencedColumnName = "userName", nullable = false, unique = true)
    private Utenti utente;
	
	@OneToOne(
			mappedBy = "cliente",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
    private Carrelli carrello;
	
	@OneToMany(mappedBy = "cliente")
	private List<Ordini> ordini;
}
