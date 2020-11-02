package br.com.pessoa.api.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseCrudRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {

    Optional<List<T>> findAllByStatus(Status status);

    Optional<T> findByIdAndStatus(ID id, Status status);

}
