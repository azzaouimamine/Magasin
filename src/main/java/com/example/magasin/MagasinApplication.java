package com.example.magasin;

import com.example.magasin.entities.Article;
import com.example.magasin.repository.ArticleRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class MagasinApplication {

    public static void main(String[] args) throws ParseException {

        ApplicationContext ctx = SpringApplication.run(MagasinApplication.class, args);
        ArticleRepo articleRepo = ctx.getBean(ArticleRepo.class);


        //Insérer les données
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "31/08/2018"; Date date = df.parse(dateInString);
        articleRepo.save(new Article("Ordinateur","4245g25g","HP NoteBook",date,"20183108-Ordinateur-4245g25g"));
        dateInString = "21/05/2017"; date = df.parse(dateInString);
        articleRepo.save(new Article("Ordinateur","42re45g","DELL Inspiron",date,"20170521-Ordinateur-42re45g"));



        //Récupérer les données
        Set<Article> setArticles = articleRepo.findByDesignation("DELL Inspiron");
        Page<Article> pageArticles1 = articleRepo.findArticleByType("Ordinateur", PageRequest.of(0,5));
        Page<Article> pageArticles2 = articleRepo.findArticles("%42%", PageRequest.of(0,5));
        String minDateInString = "19/05/2017"; Date minDate = df.parse(dateInString);
        String maxDateInString = "25/05/2017"; Date maxDate = df.parse(dateInString);
        Page<Article> pageArticles3 = articleRepo.findArticles(minDate,maxDate,PageRequest.of(0,5) );
        Article article1 = articleRepo.findArticleByDateAchat(date);


        //Afficher les données
        System.out.println("Articles de designation: DELL Inspiron");
        setArticles.forEach(article -> System.out.println(article.toString()));
        System.out.println("Articles de type: Ordinateur");
        pageArticles1.forEach(article -> System.out.println(article.toString()));
        System.out.println("Article dont la référence contient: 42");
        pageArticles2.forEach(article -> System.out.println(article.toString()));
        System.out.println("Article acheté le: 21/05/2017");
        article1.toString();
        System.out.println("Articles achetés entre: 19/05/2017 et 25/05/2017");
        pageArticles3.forEach(article -> System.out.println(article.toString()));


    }

}
