package com.example.magasin.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ligne_de_cmd {
    @Id
    private float prix;
    private int quantity;
    
    @ManyToOne
    @JoinColumn( name = "commande" )
    private Commande commande;
    
    @ManyToOne
    @JoinColumn( name = "article" )
    private Article article;

}
