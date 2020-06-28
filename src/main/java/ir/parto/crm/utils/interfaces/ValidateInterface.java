package ir.parto.crm.utils.interfaces;

import ir.parto.crm.utils.transientObject.ValidateObject;

public interface ValidateInterface<T> {
    ValidateObject validateAddNewItem(T t);

    ValidateObject validateUpdateItem(T t);

    ValidateObject deleteItem(T t);

    ValidateObject findOne(T t);

    ValidateObject findById(T t);

    ValidateObject findAll();
}
