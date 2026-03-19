package com.betacom.persistence.entity.commerce;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "recensioni",
    uniqueConstraints = {
    	@UniqueConstraint(columnNames = {"utente_id", "acquistabile_id"}),
        @UniqueConstraint(columnNames = {"utente_id", "generale_zoo"})
    }
)
public class Recensioni {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	
	@Column (nullable = false)
	private Integer voto; // 1-5 
	
	@Column (nullable = false, columnDefinition = "TEXT")
	private String testo;
	
	@Column (nullable = false, length = 50)
	private String titolo;
	
	@Column (nullable = false)
	private LocalDateTime dataCreazione = LocalDateTime.now();
	
	@Column(nullable = false)
	private Boolean generaleZoo = false;

	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false
			)
	@JoinColumn(name = "cliente_id")
	private Clienti cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "items_id")
	private Items item;
	
	
	
}