package com.kja.exam.jpaBoard.article.domain;

import com.kja.exam.jpaBoard.article.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}