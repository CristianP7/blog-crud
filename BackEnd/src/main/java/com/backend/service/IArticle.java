package com.backend.service;

import com.backend.model.Articles;

import java.util.List;

public interface IArticle extends ICrud<Articles, Integer> {

//    List<Articles> findByCreatorAndId(Long id);

    List<Articles> findByTopicName(String name);
    List<Articles> findByTitleContainsOrArticleContent_ContentContains(String title, String content);

}
