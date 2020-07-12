package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import ir.parto.crm.modules.service.model.service.ServiceAddonService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class ServiceAddonValidate implements ValidateInterface<ServiceAddon> {
    private ServiceAddonService serviceAddonService;

    @Autowired
    public ServiceAddonValidate(ServiceAddonService serviceAddonService) {
        this.serviceAddonService = serviceAddonService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceAddon serviceAddon) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(ServiceAddon serviceAddon) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(ServiceAddon serviceAddon) {
        return null;
    }

    @Override
    public ValidateObject findOne(ServiceAddon serviceAddon) {
        return null;
    }

    @Override
    public ValidateObject findById(ServiceAddon serviceAddon) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
