package com.betacom.persistence.entity.gestione;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="mangime")
public class Mangimi {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String tipoMangime;
    
    @OneToOne
    @JoinColumn(name = "animali",referencedColumnName ="id")
    private Animali animale;
    
    @ManyToOne
    @JoinColumn(name = "movimentimangimi",referencedColumnName ="id")
    private MovimentiMangimi movimentoMangime;
    
}