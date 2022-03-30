package com.example.magasin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.magasin.modele.Categorie;
import com.example.magasin.repository.CategorieRepository;

@Controller
public class CategorieController {
    
    @Autowired
    CategorieRepository categorieRipository;
    
    
    @GetMapping("/categorie/categorie")
    public String categorie(Model model) {
        model.addAttribute("categorie", new Categorie());
        model.addAttribute("categories", categorieRipository.findAll());
                
        return "categorie/categorie";
    }
    
    @PostMapping("/categorie/categorie")
    public String addCategorie(@ModelAttribute Categorie categorie) {
        categorieRipository.save(categorie);
        
        return "redirect:/categorie/categorie";
    }

}
