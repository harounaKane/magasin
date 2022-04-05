package com.example.magasin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.magasin.repository.PanierRepository;

@Controller
public class CommandeController {
    
    @Autowired
    PanierRepository panierRepository;
    
    

}
