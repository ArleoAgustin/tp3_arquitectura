package app.service;

import java.util.List;

public interface BaseService<E> {

    E findById(Long id) throws Exception;
    List<E> findAll() throws Exception;
    boolean save(E entity) throws Exception;
    boolean update(Long dni, E entity) throws Exception;
    boolean delete(Long id) throws Exception;
}
