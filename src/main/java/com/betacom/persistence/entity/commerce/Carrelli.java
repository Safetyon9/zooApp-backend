package com.betacom.persistence.entity.commerce;

import java.util.List;

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

@Setter
@Getter
@Entity
@Table(name="carrelli")
public class Carrelli {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@OneToOne
	@JoinColumn(name = "clienti",referencedColumnName ="id")
	private Clienti cliente;
	
	@OneToMany
	@JoinColumn(name = "oggetti_carrello",referencedColumnName ="id")
	private List<OggettiCarrelli> OggettoCarrello;
}
