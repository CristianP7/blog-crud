package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrud<T, ID>{

    T save(T t) throws Exception;
    T update(T t, ID id) throws Exception;
    List<T> readAll() throws Exception;
    Page<T> readAll(Pageable pageable) throws Exception;
    T readById(ID id) throws Exception;
    void delete(ID id) throws Exception;
}
