package br.com.pessoa.api.core;

import java.io.Serializable;

public interface RestController<T extends BaseEntity, ID extends Serializable> {

    T findById(ID id);

    T save(T entity);

    T edit(T entity);

    void delete(ID id);
}
