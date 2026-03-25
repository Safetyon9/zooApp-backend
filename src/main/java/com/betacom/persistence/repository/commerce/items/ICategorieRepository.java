package com.betacom.persistence.repository.commerce.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.items.Categorie;

@Repository
public interface ICategorieRepository extends JpaRepository<Categorie, Integer> {

}
