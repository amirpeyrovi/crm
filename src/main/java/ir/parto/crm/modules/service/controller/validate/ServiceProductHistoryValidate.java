package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.modules.service.model.service.ServiceProductHistoryService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class ServiceProductHistoryValidate implements ValidateInterface<ServiceProductHistory> {
    private ServiceProductHistoryService serviceProductHistoryService;

    @Autowired
    public ServiceProductHistoryValidate(ServiceProductHistoryService serviceProductHistoryService) {
        this.serviceProductHistoryService = serviceProductHistoryService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceProductHistory serviceProductHistory) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(ServiceProductHistory serviceProductHistory) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(ServiceProductHistory serviceProductHistory) {
        return null;
    }

    @Override
    public ValidateObject findOne(ServiceProductHistory serviceProductHistory) {
        return null;
    }

    @Override
    public ValidateObject findById(ServiceProductHistory serviceProductHistory) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
