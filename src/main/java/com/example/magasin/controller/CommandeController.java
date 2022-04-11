package com.example.magasin.controller;



import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.magasin.modele.Commande;
import com.example.magasin.modele.Ligne_de_cmd;
import com.example.magasin.modele.Panier;
import com.example.magasin.modele.User;
import com.example.magasin.repository.CommandeRepository;
import com.example.magasin.repository.LigneCmdRepository;
import com.example.magasin.repository.PanierRepository;
import com.example.magasin.repository.UserRepository;

@Controller
public class CommandeController {
    
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    LigneCmdRepository ligneCmdRepository;
    
    @GetMapping("/commande/add/{user}")
    public String add(
                      @PathVariable("user") int id_user,
                      RedirectAttributes attributes,
                      HttpSession session
            ) {
        User user = (User) session.getAttribute( "user" );
        
        List<Panier> panier = panierRepository.findByUser( user  ); 
        
        Commande commande = new Commande();
        commande.setUser( user );
        
        commandeRepository.save( commande );
        
        for(Panier p : panier) {
            Ligne_de_cmd ligne = new Ligne_de_cmd( 
                                                    p.getArticle().getPrix(), 
                                                    p.getQuantity(), 
                                                    commande,
                                                    p.getArticle()
                                                );  
            ligneCmdRepository.save( ligne );    
            panierRepository.delete( p );
        }
        session.setAttribute( "panier", panierRepository.findByUser( user  ) );
        attributes.addFlashAttribute( "message", "Commande ajoutée avec success!" );
        
        return "redirect:/";
    }
    
    

}
