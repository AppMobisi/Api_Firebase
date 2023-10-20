package com.example.apiffirebase.genericRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenericRepository<T, ID>{
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);

    List<T> findByField(String field, Object value);

    List<T> searchByCriteria(Map<String, Object> criteria);
}
