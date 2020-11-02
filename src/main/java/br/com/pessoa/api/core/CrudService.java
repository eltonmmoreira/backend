package br.com.pessoa.api.core;

import java.io.Serializable;

public interface CrudService<T extends BaseEntity, ID extends Serializable>
        extends SimpleCrudService<T, ID> {

}
