package com.betacom.persistence.entity.gestione;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="mangime_stocks")
public class MangimiStocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double quantita;
    
    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;
    
    @ManyToOne
    @JoinColumn(name = "mangime_id")
    private Mangimi mangime;
    
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Aree area;
}
