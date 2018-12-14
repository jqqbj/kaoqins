package com.rrx.kaoqins.admin.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "kaoqin",type = "article")
public class Article {

    private Integer id;

    private String author;

    //@Field(searchAnalyzer="ik_max_word",analyzer = "ik_max_word")
    private String title;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("author", author)
                .append("title", title)
                .append("content", content)
                .toString();
    }
}
