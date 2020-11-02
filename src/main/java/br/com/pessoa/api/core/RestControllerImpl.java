package br.com.pessoa.api.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class RestControllerImpl<T extends BaseEntity, ID extends Serializable>
        implements RestController<T, ID> {

    protected abstract CrudService<T, ID> getService();

    @Override
    @GetMapping("/{id}")
    public T findById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @Override
    @PostMapping
    public T save(@RequestBody @Valid T entity) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Map<String, String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            var key = "messageValidation";
            var map = Optional.ofNullable(errors.get(key)).orElse(new HashMap<>());
            map.put(error.getField(),
                    Objects.requireNonNull(error.getDefaultMessage())
            );
            errors.put(key, map);
        });

        return errors;
    }
}
