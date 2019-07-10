package com.example.magasin.web;

import com.example.magasin.entities.Article;
import com.example.magasin.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/Article") //nom du controlleur
public class ArticleController  {

    @Autowired
    private ArticleRepo articleRepo;

    @RequestMapping(value = "/Index") //nom de l'action
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "motCle", defaultValue = "") String mc){
        //Page<Article> listArticle = articleRepo.findAll(PageRequest.of(p, 5));
        Page<Article> listArticle = articleRepo.findArticles("%"+mc+"%", PageRequest.of(p, 5));
            int pagesCount = listArticle.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount ; i++) {
            pages[i]=i;
        }
        model.addAttribute("pages",pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageArticles", listArticle);
        return "articles";
    }

    @RequestMapping(value = "/FormArticle", method = RequestMethod.GET) //nom de l'action
    public String formArticle(Model model){
        model.addAttribute("article", new Article());
        return "formArticle";
    }

    @RequestMapping(value = "/SaveArticle", method = RequestMethod.POST) //nom de l'action
    public String save(@Valid Article art, BindingResult bindingResult, @RequestParam(name = "photo") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()){
            return "formArticle";
        }
        if (!(file.isEmpty())){
            art.setPhoto(file.getOriginalFilename());
            file.transferTo(new File("C:/Users/Admin/mesPhotosArticles"));
        }
        articleRepo.save(art);
        return "redirect:Index";
    }

}
