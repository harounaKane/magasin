package com.example.magasin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.magasin.modele.Panier;
import com.example.magasin.modele.User;
import com.example.magasin.repository.PanierRepository;
import com.example.magasin.repository.UserRepository;

@Controller
public class PanierController {
    
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    UserRepository userRepository;
    
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
        
        session.setAttribute( "panier", panierRepository.findByUser( panier.getUser() ) );
        
        return "redirect:/";
    }
    
    
    @GetMapping("/panier/{user}/{user_id}")
    public String panier(@PathVariable("user") String userName,
                         @PathVariable("user_id") int userId,
                         Model model, HttpSession session
            ) {
        
        List<Panier> panier = panierRepository.findByUser( userRepository.getById( userId ) );
        
        model.addAttribute( "paniers", panier );
        session.setAttribute( "panier", panier );
        
        return "panier/panier";
    }
    
    
    @GetMapping("/delete/{id_panier}")
    public String panier(@PathVariable("id_panier") int id_panier, HttpSession session) {
        System.out.println( id_panier );
        panierRepository.delete( panierRepository.getById( id_panier ) );
        
        User user = (User) session.getAttribute( "user" );
        
        return "redirect:/panier/" + user.getPrenom()+"/"+user.getId_user();
    }
    

}















