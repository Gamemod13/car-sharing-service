package mate.academy.car.sharing.service;

import java.util.List;
import mate.academy.car.sharing.entity.User;

public interface AbstractService<T> {
    T add(T entity);

    T getById(Long id);

    List<T> getAll();

    void delete(Long id);
    
    User update(User user);
}
