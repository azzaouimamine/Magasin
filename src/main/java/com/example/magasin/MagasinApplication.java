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
import java.util.*;

@SpringBootApplication
public class MagasinApplication {

    public static void main(String[] args) throws ParseException {

        ApplicationContext ctx = SpringApplication.run(MagasinApplication.class, args);
        ArticleRepo articleRepo = ctx.getBean(ArticleRepo.class);

        //Insérer les données
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "31/08/2018"; Date date = df.parse(dateInString);

        //Instancier 99 articles de type ordinateur
        String type = "Ordinateur";
        String reference ;
        String designation ;
        Date dateAchat ;
        String photo ;
        Random rand = new Random();
        int n, jour, mois, an;
        for (int i = 0; i < 99 ; i++){
            n = rand.nextInt(9)+1; // [1 - 9] Obtenir un nombre entre [0 - 8] puis ajouter 1
            jour = rand.nextInt(30)+1; // [1 - 30]
            mois = rand.nextInt(12)+1; // [1 - 12]
            an = rand.nextInt(5)+2015; // [2015 - 2019]
            reference = String.valueOf(n) + String.valueOf(jour) + String.valueOf(n) + String.valueOf(n) + String.valueOf(mois) + String.valueOf(n) ;
            if(i%2 == 0){
                designation = "HP NoteBook";
                photo = "HP_NoteBook.png";
            }
            else{
                designation = "DELL Inspiron";
                photo = "DELL_Inspiron.png";
            }
            String dateAchatString = String.valueOf(jour) + "/" + String.valueOf(mois) + "/" + String.valueOf(an);
            dateAchat = df.parse(dateAchatString);
            articleRepo.save(new Article(type,reference,designation,dateAchat,photo));
        }

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
        System.out.println("TODO: Article acheté le: 21/05/2017 ");
        System.out.println("Articles achetés entre: 19/05/2017 et 25/05/2017");
        pageArticles3.forEach(article -> System.out.println(article.toString()));


    }

}
