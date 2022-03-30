package com.example.magasin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magasin.modele.Article;
import com.example.magasin.modele.Categorie;

public interface ArticleRepository extends JpaRepository<Article, Integer>{

    List<Article> findByCategorie( Categorie categorie );

}
