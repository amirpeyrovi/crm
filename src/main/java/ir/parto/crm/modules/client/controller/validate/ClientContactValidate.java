package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;

public class ClientContactValidate implements ValidateInterface<ClientContact> {
    @Override
    public ValidateObject validateAddNewItem(ClientContact clientContact) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(ClientContact clientContact) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(ClientContact clientContact) {
        return null;
    }

    @Override
    public ValidateObject findOne(ClientContact clientContact) {
        return null;
    }

    @Override
    public ValidateObject findById(ClientContact clientContact) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
