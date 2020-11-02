package br.com.pessoa.api.core;

import br.com.pessoa.api.exception.Error;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Log4j2
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
    public Error handleError(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();

        var error = new Error(e.getMessage(), e.getClass().getSimpleName());
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        error.setParameters(errors);
        log.error(e);
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Error handleError(Exception e) {
        log.error(e);
        if (e.getCause() != null) {
            return new Error(e.getCause().getMessage(), e.getCause().getClass().getSimpleName());
        }
        return new Error(e.getMessage(), e.getClass().getSimpleName());
    }
}
