package com.example.magasin.modele;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Categorie {
    
    @Id
    private String categorie;
    
    @OneToMany( mappedBy = "categorie" )
    private List<Article> articles = new ArrayList<>();

    @Override
    public String toString() {
        return "Categorie [categorie=" + categorie +"]";
    }

}
