package mate.academy.car.sharing.service;

import java.util.List;

public interface AbstractService<T> {
    T add(T entity);

    T getById(Long id);

    List<T> getAll();

    T update (T entity);

    void delete(Long id);
}
