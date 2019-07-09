package com.example.magasin.repository;

import com.example.magasin.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    public Set<Article> findByDesignation(String designation);

    public Page<Article> findArticleByType(String type, Pageable pageable);

    @Query("select a from Article as a where a.reference like :x")
    public Page<Article> findArticles(@Param( value = "x") String reference,
                                      Pageable pageable);

    public Article findArticleByDateAchat(Date dateAchat);

    @Query("select a from Article as a where :dateMin < a.dateAchat and a.dateAchat < :dateMax ")
    public  Page<Article> findArticles(@Param( value = "dateMin") Date dateMinimum,
                                       @Param( value = "dateMax") Date dateMaximum,
                                       Pageable pageable );

}
