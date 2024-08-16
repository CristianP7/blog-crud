package com.backend.service.impl;

import com.backend.model.Articles;
import com.backend.repository.IArticlesRepo;
import com.backend.repository.IGenericRepo;
import com.backend.service.IArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class ArticlesImpl extends CrudImpl<Articles, Integer> implements IArticle {

    private final IArticlesRepo repo;

    @Override
    protected IGenericRepo<Articles, Integer> getRepo() {
        return repo;
    }
}
