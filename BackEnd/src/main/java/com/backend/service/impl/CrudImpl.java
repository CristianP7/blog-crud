package com.backend.service.impl;

import com.backend.repository.IGenericRepo;
import com.backend.service.ICrud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.util.List;

public abstract class CrudImpl<T, ID> implements ICrud<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {

        Class<?> clazz = t.getClass();
        String className = t.getClass().getSimpleName();
        String methodName = "setId" + className;
        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);

        getRepo().findById(id).orElseThrow(() -> new Exception("Id Not Found"));
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public Page<T> readAll(Pageable pageable) throws Exception {
        return getRepo().findAll(pageable);
    }

    @Override
    public T readById(ID id) throws Exception {
        //orElse temporal
        return getRepo().findById(id).orElseThrow(() -> new Exception("Id Not Found"));

    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new Exception("Id Not Found"));
        getRepo().deleteById(id);
    }
}
