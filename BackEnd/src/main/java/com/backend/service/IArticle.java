package com.backend.service;

import com.backend.model.Articles;

import java.util.List;

public interface IArticle {

//    List<Articles> findByCreatorAndId(Long id);

    List<Articles> findByTopicName(String name);

}
