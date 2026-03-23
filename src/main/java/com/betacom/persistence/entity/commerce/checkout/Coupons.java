package com.betacom.persistence.entity.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.betacom.enums.TipoCoupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coupons")
public class Coupons {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, unique = true)
    private String codice;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCoupon tipo;
	
	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valore;
	
	@Column(nullable = false)
    private Boolean attivo = true;
	
	@Column(name = "inizio_validità", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "fine_validità", nullable = false)
    private LocalDate dataFine;
    
    @OneToMany(
    		mappedBy = "coupon",
    		fetch = FetchType.LAZY
    		)
    private List<Pagamenti> pagamenti; 
}
