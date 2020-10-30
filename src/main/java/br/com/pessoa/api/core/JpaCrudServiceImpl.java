package br.com.pessoa.api.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Log4j2
@Service
public abstract class JpaCrudServiceImpl<T, ID extends Serializable>
        implements CrudService<T, ID> {

    private final Class<T> persistentClass;

    protected abstract JpaRepository<T, ID> getData();

    protected void postFindById(T entity) {

    }

    protected void postSave(T entity) {

    }

    public JpaCrudServiceImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        try {
            entity = getData().save(entity);
            log.info("[save]-" + persistentClass + "[entity]-" + entity.toString());
            postSave(entity);
            return entity;
        } catch (Exception e) {
            log.error("[save]-" + persistentClass);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        log.info("[findAll]-" + persistentClass);
        return getData().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        log.info("[findById]-" + persistentClass + "[id]-" + id);
        var entity = getData().findById(id).orElse(null);
        postFindById(entity);
        return entity;
    }

    @Override
    public void delete(ID id) {
        log.info("[delete]-" + persistentClass + "[id]-" + id);
        getData().deleteById(id);
    }
}
