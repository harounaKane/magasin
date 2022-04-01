package com.example.magasin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.magasin.modele.Panier;
import com.example.magasin.repository.PanierRepository;

@Controller
public class CommandeController {
    
    @Autowired
    PanierRepository panierRepository;
    
    @PostMapping("/add/panier")
    public String addPanier(@ModelAttribute Panier panier, HttpSession session) {
        
        panierRepository.save( panier );
        
        session.setAttribute( "panier", panierRepository.findAll() );
        
        return "redirect:/";
    }

}
