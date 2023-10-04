package app.service;

import java.util.List;
import java.util.Optional;

public interface Service<E> {

    E findBy(Long x) throws Exception;
    Optional findAll() throws Exception;
    boolean save(E entity) throws Exception;
    boolean update(Long dni, E entity) throws Exception;
    boolean delete(Long id) throws Exception;
}
