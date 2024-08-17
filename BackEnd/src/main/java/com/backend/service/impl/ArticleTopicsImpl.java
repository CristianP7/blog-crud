package com.backend.service.impl;

import com.backend.model.ArticleTopics;
import com.backend.repository.IArticleTopicsRepo;
import com.backend.repository.IGenericRepo;
import com.backend.service.IArticleTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleTopicsImpl extends CrudImpl<ArticleTopics, Integer> implements IArticleTopics {

    private final IArticleTopicsRepo repo;

    @Override
    protected IGenericRepo<ArticleTopics, Integer> getRepo() {
        return repo;
    }
}
