package com.example.magasin.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticle" , length = 100)
    private Long idArticle;
    @Column(name = "TypeArticle" , length = 100)
    private String type;
    @Column(name = "ReferenceArticle" , length = 50)
    @NotEmpty
    @Size(min = 5, max = 50)
    private String reference;
    @Column(name = "designationArticle" , length = 100)
    private String designation;
    @Column(name = "dateAchatArticle" , length = 100)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAchat;
    @Column(name = "photoArticle" , length = 100)
    private String photo;

    public Article() {
        super();
    }

    public Article(String type, String reference, String designation, Date dateAchat, String photo) {
        this.type = type;
        this.reference = reference;
        this.designation = designation;
        this.dateAchat = dateAchat;
        this.photo = photo;
    }


    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", type='" + type + '\'' +
                ", reference='" + reference + '\'' +
                ", designation='" + designation + '\'' +
                ", dateAchat=" + dateAchat +
                ", photo='" + photo + '\'' +
                '}';
    }
}
