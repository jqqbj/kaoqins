package com.rrx.kaoqins.admin.repository;

import com.rrx.kaoqins.admin.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 参考文档：https://docs.spring.io/spring-data/elasticsearch/docs/3.0.12.RELEASE/reference/html/
 */

public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {

    List<Article> findByTitleLike(String title);

}
