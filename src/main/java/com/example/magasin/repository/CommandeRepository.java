package com.example.magasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magasin.modele.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

}
