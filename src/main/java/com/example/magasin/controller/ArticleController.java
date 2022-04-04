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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.magasin.mesService.MyServices;
import com.example.magasin.modele.Article;
import com.example.magasin.modele.Categorie;
import com.example.magasin.modele.Panier;
import com.example.magasin.repository.ArticleRepository;
import com.example.magasin.repository.CategorieRepository;

@Controller
public class ArticleController {
    
    @Autowired
    CategorieRepository categorieRipository;
    @Autowired
    ArticleRepository articleRepository;
    
    
    @GetMapping("/article/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        
        return "article/articles";
    }
    
    @GetMapping("/article/detail/{id_art}")
    public String article(Model model, @PathVariable("id_art") int id_art) {
        model.addAttribute("article", articleRepository.getById(id_art) );
        
        return "/article/article";
    }
    
    @GetMapping("/article/articleToPanier/{id_art}")
    public String articleToPanier(Model model, 
                                   @PathVariable("id_art") int id_art, 
                                   HttpSession session
                                   ) {
        
        model.addAttribute("article", articleRepository.getById(id_art) );
    
        model.addAttribute( "panier", new Panier() );
        return "/article/articleToPanier";
    }

    @GetMapping("/article/new")
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categorieRipository.findAll());
        
        return "article/articleForm";
    }
    
    @PostMapping("/article/ByCategorie")
    public String articleByCategorie(Model model, @ModelAttribute("categorie") Categorie categorie) {
        model.addAttribute( "articles", articleRepository.findByCategorie(categorie) );
        model.addAttribute( "categorie", categorie);
        model.addAttribute( "categories", categorieRipository.findAll() );
        
        return "index";
    }
    
    @PostMapping("/article/new")
    public String article(@Valid @ModelAttribute("article") Article article,
                          BindingResult result, 
                          @RequestParam("photo") MultipartFile multipartFile,
                          Model model
            ) throws IOException {
        
        String fileName = StringUtils.cleanPath( multipartFile.getOriginalFilename() );
        
        if( result.hasErrors() || fileName.isEmpty() || articleRepository.findByLibelle(article.getLibelle()) != null ) {
            model.addAttribute("article", article);
            model.addAttribute("categories", categorieRipository.findAll());
            model.addAttribute( "errors", result );
            model.addAttribute( "imgError", "Au moins une image" );
            
            if(articleRepository.findByLibelle(article.getLibelle()) != null)
                model.addAttribute( "libelleUnique", "Ce libellé existe déjà!" );

            return "article/articleForm";
        }

        if( !fileName.isEmpty() ) {
            String uploadDir = "src\\main\\resources\\static\\imgArticle\\";
            
           MyServices.uploadFile(fileName, uploadDir, multipartFile);
            
            article.setLogo(fileName);
        }
        articleRepository.save(article);
       
        return "redirect:/";
    } 
}
