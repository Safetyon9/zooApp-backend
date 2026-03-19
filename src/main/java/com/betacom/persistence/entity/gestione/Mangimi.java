package com.betacom.persistence.entity.gestione;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="mangimi")
public class Mangimi {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String tipoMangime;
    
    @ManyToMany
    @JoinTable(
        name = "animali_mangimi",
        joinColumns = @JoinColumn(name = "mangime_id"),
        inverseJoinColumns = @JoinColumn(name = "animale_id")
    )
    private List<Animali> animali;
    
    @OneToMany(mappedBy = "mangime")
    private List<MovimentiMangimi> movimenti;   
    
}