package com.example.magasin.web;

import com.example.magasin.entities.Article;
import com.example.magasin.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/Article") //nom du controlleur
public class ArticleController  {

    @Autowired
    private ArticleRepo articleRepo;

    @RequestMapping(value = "/Index") //nom de l'action
    public String index(Model model,@RequestParam(name = "page", defaultValue = "0") int p){
        Page<Article> listArticle = articleRepo.findAll(PageRequest.of(p, 5));
        model.addAttribute("pageArticles", listArticle);
        return "articles";
    }
}
