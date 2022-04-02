package com.kja.exam.jpaBoard.article.controller;

import com.kja.exam.jpaBoard.article.dao.ArticleRepository;
import com.kja.exam.jpaBoard.article.domain.Article;
import com.kja.exam.jpaBoard.user.dao.UserRepository;
import com.kja.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("detail")
    @ResponseBody
    // EX : http://localhost:8082/usr/article/detail?id=2 => id 매개변수에 2L 이 들어옵니다.
    public Article showDetail(long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.get();
    }

    @RequestMapping("doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if ( title != null ) {
            article.setTitle(title);
        }

        if ( body != null ) {
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());
        articleRepository.save(article);

        return article;
    }

    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(long id) {

        if ( articleRepository.existsById(id) == false ) {
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }

        articleRepository.deleteById(id);
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    @RequestMapping("doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if ( title == null || title.trim().length() == 0 ) {
            return "제목을 입력해주세요.";
        }

        title = title.trim();

        if ( body == null || body.trim().length() == 0 ) {
            return "내용을 입력해주세요.";
        }

        body = body.trim();

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        User user = userRepository.findById(1L).get();
        article.setUser(user);

        articleRepository.save(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }
}