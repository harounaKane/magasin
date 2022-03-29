package com.example.magasin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.magasin.mesService.MyServices;
import com.example.magasin.modele.Article;
import com.example.magasin.repository.ArticleRepository;
import com.example.magasin.repository.CategorieRepository;

@Controller
public class ArticleController {
    
    @Autowired
    CategorieRepository categorieRipository;
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categorieRipository.findAll());
        
        return "article/articleForm";
    }
    
    @PostMapping("/article/new")
    public String article(@RequestParam("categorie") String categorie, @ModelAttribute Article article, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        
        String fileName = StringUtils.cleanPath( multipartFile.getOriginalFilename() );
        
        if( !fileName.isEmpty() ) {
            String uploadDir = "src\\main\\resources\\static\\imgArticle\\";
            
           MyServices.uploadFile(fileName, uploadDir, multipartFile);
            
            article.setLogo(fileName);
        }
        article.setCategorie( categorieRipository.findByCategorie(categorie) );
        articleRepository.save(article);
       
        return "redirect:/article/new";
    }
}
