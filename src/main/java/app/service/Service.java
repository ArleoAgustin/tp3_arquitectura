package app.service;

import java.util.List;

public interface Service<E> {

    E findBy(Long x) throws Exception;
    List<E> findAll() throws Exception;
    boolean save(E entity) throws Exception;
    boolean update(Long dni, E entity) throws Exception;
    boolean delete(Long id) throws Exception;
}
