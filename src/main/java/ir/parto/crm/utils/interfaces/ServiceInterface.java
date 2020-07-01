package ir.parto.crm.utils.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ServiceInterface<T> {
    T addNewItem(T t);

    T updateItem(T t) throws InvocationTargetException, IllegalAccessException;

    T deleteItem(T t);

    List<T> findAllItem();

    Page<T> findAllItem(Pageable pageable);

    T findOne(T t);

    T findById(Long id);

    Boolean existsById(Long id);
}
