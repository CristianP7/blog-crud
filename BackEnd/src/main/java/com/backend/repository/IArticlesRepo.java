package com.backend.repository;

import com.backend.model.Articles;

import java.util.List;

public interface IArticlesRepo extends IGenericRepo<Articles, Integer> {

//    List<Articles> findByCreatorAndId(Long id);

    List<Articles> findByTopicName(String name);
    List<Articles> findByTitleContainsOrArticleContent_ContentContains(String title, String content);
}