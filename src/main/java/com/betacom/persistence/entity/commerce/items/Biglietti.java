package com.betacom.persistence.entity.commerce.items;

import com.betacom.persistence.entity.commerce.Items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="biglietti")
public class Biglietti extends Items{

	@Column(nullable = false)
    private String tipo;
}
