package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceProductParameterValue;
import ir.parto.crm.modules.service.model.service.ServiceProductParameterValueService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class ServiceProductParameterValueValidate implements ValidateInterface<ServiceProductParameterValue> {
    private ServiceProductParameterValueService serviceProductParameterValueService;

    @Autowired
    public ServiceProductParameterValueValidate(ServiceProductParameterValueService serviceProductParameterValueService) {
        this.serviceProductParameterValueService = serviceProductParameterValueService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceProductParameterValue serviceProductParameterValue) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(ServiceProductParameterValue serviceProductParameterValue) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(ServiceProductParameterValue serviceProductParameterValue) {
        return null;
    }

    @Override
    public ValidateObject findOne(ServiceProductParameterValue serviceProductParameterValue) {
        return null;
    }

    @Override
    public ValidateObject findById(ServiceProductParameterValue serviceProductParameterValue) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
