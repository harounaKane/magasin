package com.example.magasin.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_user;  
    
    @Size(min = 2, max = 20)
    @NotNull
    @NotBlank
	private String prenom;
    
    @Size(min = 2, max = 20)
	private String nom;    

    @Size(min = 4, max = 20)
	private String login;

    @Size(min = 4, max = 20)
	private String mdp;

	private String statut = "USER";
	private String avatar;
	
	@NotNull
	private String sexe;
	
	@OneToMany(mappedBy = "user")
    private List<Panier> panier = new ArrayList<>();
	
	@OneToMany( mappedBy = "user" )
	private List<Commande> commandes = new ArrayList<>();
	

}
