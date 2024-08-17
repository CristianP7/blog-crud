package com.backend.service.impl;

import com.backend.model.ArticleContent;
import com.backend.repository.IArticleContentRepo;
import com.backend.repository.IGenericRepo;
import com.backend.service.IArticleContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleContentImpl extends CrudImpl<ArticleContent, Integer> implements IArticleContent {

    private final IArticleContentRepo repo;

    @Override
    protected IGenericRepo<ArticleContent, Integer> getRepo() {
        return repo;
    }
}
