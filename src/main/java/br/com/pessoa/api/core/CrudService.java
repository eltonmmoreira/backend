package br.com.pessoa.api.core;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {

    T save(T entity);

    List<T> findAll();

    T findById(ID id);

    void delete(ID id);
}
