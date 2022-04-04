package com.example.magasin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magasin.modele.Article;
import com.example.magasin.modele.Panier;
import com.example.magasin.modele.User;

public interface PanierRepository extends JpaRepository<Panier, Integer> {


    Panier findByUserAndArticle( User user, Article article );

    List<Panier> findByUser( User user );

}
