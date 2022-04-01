package com.example.magasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magasin.modele.Panier;

public interface PanierRepository extends JpaRepository<Panier, Integer> {

}
