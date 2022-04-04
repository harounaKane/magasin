package com.example.magasin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.magasin.modele.Panier;
import com.example.magasin.modele.User;
import com.example.magasin.repository.PanierRepository;

@Controller
public class CommandeController {
    
    @Autowired
    PanierRepository panierRepository;
    
    @PostMapping("/add/panier")
    public String addPanier(@ModelAttribute Panier panier, 
                            HttpSession session, 
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request
                            ) {
        
        
        if( session.getAttribute( "user" ) == null ) {
            redirectAttributes.addFlashAttribute( "message", "Veuillez vous connecter pour ajouter votre article" );
            redirectAttributes.addFlashAttribute( "referer", request.getHeader( "referer" ) );
            
            return "redirect:/user/connexion";
        }
        
        panier.setUser( (User) session.getAttribute( "user" ) );
        
        Panier inPanier = panierRepository.findByUserAndArticle(panier.getUser(), panier.getArticle());
       
        if( inPanier != null ) {
            panier.setQuantity( panier.getQuantity() + inPanier.getQuantity() );
            panier.setId( inPanier.getId() );
        }
        
       panierRepository.save( panier );
        
        session.setAttribute( "panier", panierRepository.findAll() );
        
        return "redirect:/";
    }

}
