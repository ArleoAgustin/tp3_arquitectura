package app.service;


import org.springframework.stereotype.Service;

import java.util.List;

public interface BaseService<E> {

    List<E> findAll() throws Exception;
    E findBydni(Long dni) throws Exception;
    void insert(E entity) throws Exception;
    void update(Long dni, E entity) throws Exception;
    void delete(Long id) throws Exception;
}
