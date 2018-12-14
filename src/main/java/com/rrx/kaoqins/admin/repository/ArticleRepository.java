package com.rrx.kaoqins.admin.repository;

import com.rrx.kaoqins.admin.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * 参考文档：https://docs.spring.io/spring-data/elasticsearch/docs/3.0.12.RELEASE/reference/html/
 */

public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {

    List<Article> findByTitleLike(String title);

    @Query("{ \"match\" : { \"title\" : \"?0\" }}")
    Page<Article> findByTitle(String title, Pageable pageable);

    //不支持
    @Query("select u from Article u")
    Stream<Article> streamAllPaged();
}
