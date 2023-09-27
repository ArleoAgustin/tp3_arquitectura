package app.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RepoBase<T, ID extends Serializable> extends org.springframework.data.repository.Repository<T,ID>{

    void delete( T deleted);
    List<T> findAll();
    Optional<T> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    T save( T persisted);
}