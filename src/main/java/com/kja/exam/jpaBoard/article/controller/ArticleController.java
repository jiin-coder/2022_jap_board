package com.kja.exam.jpaBoard.article.controller;

import com.kja.exam.jpaBoard.article.dao.Article;
import com.kja.exam.jpaBoard.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }
}