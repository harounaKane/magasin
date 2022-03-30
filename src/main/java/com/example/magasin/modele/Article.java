package com.example.magasin.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_art;
    @NotBlank
    @Size(min = 2, max = 15)
    private String libelle;
    
    @Min(1)
    private float prix;
    @Min(1)
    private int quantity;
    private String logo;
    private String description;
    
    @ManyToOne
    @JoinColumn( name = "categorie", referencedColumnName = "categorie" )
    private Categorie categorie;

    @Override
    public String toString() {
        return "Article [id_art=" + id_art + ", libelle=" + libelle + ", prix=" + prix + ", quantity=" + quantity
                + ", logo=" + logo + ", description=" + description + "]";
    }
    
    public String getDescription() {
        return "Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée à titre provisoire pour calibrer une mise en page, le texte définitif venant remplacer le faux-texte dès qu'il est prêt ou que la mise en page est achevée. Généralement, on utilise un texte en faux latin, le Lorem ipsum ou Lipsum.";
    }
    
    public String getImage() {return "/imgArticle/"+logo;}

    
}
