package br.com.pessoa.api.core;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class RestControllerImpl<T, ID extends Serializable>
        implements RestController<T, ID> {

    protected abstract CrudService<T, ID> getService();

    @Override
    @GetMapping("/{id}")
    public T findById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @Override
    @PostMapping
    public T save(@RequestBody T entity) {
        return getService().save(entity);
    }

    @Override
    @PutMapping
    public T edit(@RequestBody T entity) {
        return getService().save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }
}
