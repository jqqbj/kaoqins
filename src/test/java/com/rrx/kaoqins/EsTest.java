package com.rrx.kaoqins;

import com.rrx.kaoqins.admin.model.Article;
import com.rrx.kaoqins.admin.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

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
        //article.setId(1);
        article.setAuthor("强哥1");
        article.setTitle("强哥1到底是好人吗？");
        article.setContent("强哥1应该是好人");
        articleRepository.index(article);
    }

    @Test
    public void search(){
        List<Article> articleList = articleRepository.findByTitleLike("强哥");
        articleList.forEach(System.out::println);

        Pageable page = PageRequest.of(0,10);
        Page<Article> articleList2 = articleRepository.findByTitle("强哥", page);
        articleList2.forEach(System.out::println);

        Stream<Article> stream =  articleRepository.streamAllPaged();
        stream.forEach(System.out::print);
    }

}
