package com.example.magasin.mesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.magasin.modele.Panier;
import com.example.magasin.repository.PanierRepository;

@RestController
public class PanierRequestController {

    @Autowired
    PanierRepository panierRepository;

    @RequestMapping( "/update/panier/{id_panier}/{qtt}" )
    public Panier updatePanier(
            HttpSession session,
            @PathVariable( "id_panier" ) int id_panier,
            @PathVariable( "qtt" ) int qtt,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request ) {

        if ( session.getAttribute( "user" ) == null ) {
            redirectAttributes.addFlashAttribute( "message", "Veuillez vous connecter pour ajouter votre article" );
            redirectAttributes.addFlashAttribute( "referer", request.getHeader( "referer" ) );

            // return "redirect:/user/connexion";
        }

        Panier panier = panierRepository.getById( id_panier );

        if ( panier != null ) {
            panier.setQuantity( qtt );
            panierRepository.save( panier );
        }

        return panier;
    }

}
