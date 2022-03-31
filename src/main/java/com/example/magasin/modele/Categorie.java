package com.example.magasin.modele;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Categorie {
    
    @Id
    @NotNull
    @Size(min = 2, max = 20)
    private String categorie;
    
    @OneToMany( mappedBy = "categorie" )
    private List<Article> articles = new ArrayList<>();

    @Override
    public String toString() {
        return "Categorie [categorie=" + categorie + ", articles= "+ articles +"]";
    }

}
