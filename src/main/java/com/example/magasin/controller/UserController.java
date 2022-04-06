package com.example.magasin.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.magasin.mesService.MyServices;
import com.example.magasin.modele.Categorie;
import com.example.magasin.modele.User;
import com.example.magasin.repository.ArticleRepository;
import com.example.magasin.repository.CategorieRepository;
import com.example.magasin.repository.PanierRepository;
import com.example.magasin.repository.UserRepository;

@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategorieRepository categorieRipository;
    @Autowired
    ArticleRepository articleRepository;  
    @Autowired
    PanierRepository panierRepository;
    
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute( "categorie", new Categorie() );
        model.addAttribute( "categories", categorieRipository.findAll() );
        model.addAttribute("articles", articleRepository.findAll());
        
        return "index";
    }
    
    @GetMapping("/user/inscription")
    public String inscriptionForm(Model model) {
        model.addAttribute( "user", new User() );
        
        return "user/inscription";
    }
    
    @GetMapping("/user/connexion")
    public String connexionForm() {
        
        return "user/connexion";
    }
    
    @PostMapping("/user/connexion")
    public String connexion(@RequestParam String login, 
                            @RequestParam String mdp, 
                            @RequestParam(required = false) String referer, 
                            HttpSession session
                            ) {
        
        User user = userRepository.findUserByLoginAndMdp(login, mdp);
        session.setAttribute( "user", user );
        session.setAttribute( "panier", panierRepository.findByUser(user) );
        
        if( referer != null ) {
            return "redirect:" + referer;
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/user/logOut")
    public String logOut(HttpSession session) {
        session.invalidate();
        
        return "redirect:/";
    }
    
    
    
    @PostMapping("/user/inscription")
    public String inscription(Model model, 
                @Valid @ModelAttribute User user,
                BindingResult result,
                @RequestParam("photo") MultipartFile multipartFile,
                @RequestParam("mdpBis") String mdpBis
                ) throws IOException {

        if( result.hasErrors() || !user.getMdp().equals( mdpBis ) ) {
            model.addAttribute( "user", user );
            model.addAttribute( "errors", result );
            model.addAttribute( "mdpConfirme", "les deux mdp ne correspondent pas" );
            
            return "user/inscription";
        }
        
        if( userRepository.findUserByLogin(user.getLogin()) != null ) {
            model.addAttribute( "user", user );
            model.addAttribute( "unique", "Ce login existe déjà" );
            
            return "user/inscription";
        }
        
        String fileName = StringUtils.cleanPath( multipartFile.getOriginalFilename() );
        
        if( !fileName.isEmpty() ) {
            String uploadDir = "src\\main\\resources\\static\\imgUser\\";
            
            MyServices.uploadFile(fileName, uploadDir, multipartFile);
            
            user.setAvatar( fileName );
        }
        
       // userRepository.save( user );
        
        return "redirect:/";
    }

}
