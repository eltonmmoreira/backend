package br.com.pessoa.api.core;

import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Log4j2
public abstract class JpaCrudServiceImpl<T extends BaseEntity, ID extends Serializable>
        extends JpaBaseCrudServiceImpl<T, ID> implements CrudService<T, ID> {

    protected abstract BaseCrudRepository<T, ID> getData();

    @Override
    public List<T> findAll() {
        return getData().findAllByStatus(Status.ATIVO).orElse(Collections.emptyList());
    }

    @Override
    public T findById(ID id) {
        var entity = getData().findByIdAndStatus(id, Status.ATIVO).orElse(null);
        postFindById(entity);
        return entity;
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setStatus(Status.ATIVO);
        }
        return super.save(entity);
    }

    @Override
    public void delete(ID id) {
        getData().findById(id).ifPresent(entity -> {
            entity.setStatus(Status.EXCLUIDO);
            getData().save(entity);
        });
    }
}
