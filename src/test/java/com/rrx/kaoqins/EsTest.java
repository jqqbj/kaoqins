package com.rrx.kaoqins;

import com.rrx.kaoqins.admin.model.Article;
import com.rrx.kaoqins.admin.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.util.List;

/**
 * @Author JQQ
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void contextLoads(){
        Article article = new Article();
        article.setId(1);
        article.setAuthor("强哥");
        article.setTitle("强哥到底是好人吗？");
        article.setContent("强哥应该是好人");
        articleRepository.index(article);
    }

    @Test
    public void search(){
        List<Article> articleList = articleRepository.findByTitleLike("强哥");
        articleList.forEach(System.out::print);
    }

}
