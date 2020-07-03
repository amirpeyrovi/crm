package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.stereotype.Component;

@ValidationAnnotation
@Component
public class ServerParameterValidate implements ValidateInterface<ServerParameter> {
    @Override
    public ValidateObject validateAddNewItem(ServerParameter serverParameter) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(ServerParameter serverParameter) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(ServerParameter serverParameter) {
        return null;
    }

    @Override
    public ValidateObject findOne(ServerParameter serverParameter) {
        return null;
    }

    @Override
    public ValidateObject findById(ServerParameter serverParameter) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
