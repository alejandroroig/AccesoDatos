package dao;

import java.util.List;

public interface DAO<T, K> {
    List<T> findAll();
    T findById(K id);
    void save(T t);
    void update (T id);
    void deleteById (K id);
}
