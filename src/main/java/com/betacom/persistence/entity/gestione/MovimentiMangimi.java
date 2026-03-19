package com.betacom.persistence.entity.gestione;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="movimenti_mangime")
public class MovimentiMangimi {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private Boolean movimento;
    //true entrata mangime false uscita
    @ManyToOne
    @JoinColumn(name = "dipendenti",referencedColumnName ="id")
    private Dipendenti dipendente;
    
    @OneToMany
    @JoinColumn(name = "mangimi",referencedColumnName ="id")
    private List<Mangimi> mangime;
    
    
    
}