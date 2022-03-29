package com.example.magasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magasin.modele.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, String>{

    Categorie findByCategorie( String categorie );

}
