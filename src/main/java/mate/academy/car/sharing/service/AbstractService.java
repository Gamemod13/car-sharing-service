package mate.academy.car.sharing.service;

import java.util.List;

public interface AbstractService<T> {
    T add(T entity);

    T getById(Long id);

    List<T> getAll();

    void delete(Long id);

    T update(T t);
}
