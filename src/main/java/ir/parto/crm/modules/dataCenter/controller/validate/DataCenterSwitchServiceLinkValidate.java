package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchServiceLink;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterSwitchServiceLinkValidate implements ValidateInterface<DataCenterSwitchServiceLink> {
    private DataCenterSwitchServiceLinkService dataCenterSwitchServiceLinkService;
    private DataCenterSwitchService dataCenterSwitchService;
    private ServiceService serviceService;

    @Autowired
    public DataCenterSwitchServiceLinkValidate(DataCenterSwitchServiceLinkService dataCenterSwitchServiceLinkService, DataCenterSwitchService dataCenterSwitchService, ServiceService serviceService) {
        this.dataCenterSwitchServiceLinkService = dataCenterSwitchServiceLinkService;
        this.dataCenterSwitchService = dataCenterSwitchService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchServiceLink == null) {
            errorList.add("SwitchServiceLink object is nul");
        } else {
            if (dataCenterSwitchServiceLink.getDataCenterSwitch() == null) {
                errorList.add("Switch object is nul");
            } else {
                if (dataCenterSwitchServiceLink.getService() == null) {
                    errorList.add("Service object is nul");
                } else {
                    if (!this.dataCenterSwitchService.existsById(dataCenterSwitchServiceLink.getDataCenterSwitch().getSwitchId())) {
                        errorList.add("Switch Id not defined");
                    }

                    if (!this.serviceService.existsById(dataCenterSwitchServiceLink.getService().getServiceId())) {
                        errorList.add("Service Id not defined");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchServiceLink == null) {
            errorList.add("SwitchServiceLink object is nul");
        } else {
            if (dataCenterSwitchServiceLink.getDataCenterSwitch() == null) {
                errorList.add("Switch object is nul");
            } else {
                if (dataCenterSwitchServiceLink.getService() == null) {
                    errorList.add("Service object is nul");
                } else {
                    if (!this.dataCenterSwitchServiceLinkService.existsById(dataCenterSwitchServiceLink.getSwitchServiceLinkId())) {
                        errorList.add("SwitchServiceLink Id not defined");
                    }

                    if (!this.dataCenterSwitchService.existsById(dataCenterSwitchServiceLink.getDataCenterSwitch().getSwitchId())) {
                        errorList.add("Switch Id not defined");
                    }

                    if (!this.serviceService.existsById(dataCenterSwitchServiceLink.getService().getServiceId())) {
                        errorList.add("Service Id not defined");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchServiceLink == null) {
            errorList.add("SwitchServiceLink object is nul");
        } else {
            if (!this.dataCenterSwitchServiceLinkService.existsById(dataCenterSwitchServiceLink.getSwitchServiceLinkId())) {
                errorList.add("SwitchServiceLink Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchServiceLink == null) {
            errorList.add("SwitchServiceLink object is nul");
        } else {
            if (!this.dataCenterSwitchServiceLinkService.existsById(dataCenterSwitchServiceLink.getSwitchServiceLinkId())) {
                errorList.add("SwitchServiceLink Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchServiceLink == null) {
            errorList.add("SwitchServiceLink object is nul");
        } else {
            if (!this.dataCenterSwitchServiceLinkService.existsById(dataCenterSwitchServiceLink.getSwitchServiceLinkId())) {
                errorList.add("SwitchServiceLink Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
