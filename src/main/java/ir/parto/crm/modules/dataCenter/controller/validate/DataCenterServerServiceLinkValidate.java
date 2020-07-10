package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerServiceLink;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterServerServiceLinkValidate implements ValidateInterface<DataCenterServerServiceLink> {
    private DataCenterServerServiceLinkService dataCenterServerServiceLinkService;
    private DataCenterServerService dataCenterServerService;
    private ServiceService serviceService;

    @Autowired
    public DataCenterServerServiceLinkValidate(DataCenterServerServiceLinkService dataCenterServerServiceLinkService, DataCenterServerService dataCenterServerService, ServiceService serviceService) {
        this.dataCenterServerServiceLinkService = dataCenterServerServiceLinkService;
        this.dataCenterServerService = dataCenterServerService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterServerServiceLink dataCenterServerServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerServiceLink == null) {
            errorList.add("ServerServiceLink object is nul");
        } else {
            if (dataCenterServerServiceLink.getService() == null) {
                errorList.add("Service object is nul");
            } else {
                if (dataCenterServerServiceLink.getDataCenterServer() == null) {
                    errorList.add("Server object is nul");
                } else {
                    if (!this.dataCenterServerService.existsById(dataCenterServerServiceLink.getDataCenterServer().getServerId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (!this.serviceService.existsById(dataCenterServerServiceLink.getService().getServiceId())) {
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
    public ValidateObject validateUpdateItem(DataCenterServerServiceLink dataCenterServerServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerServiceLink == null) {
            errorList.add("ServerServiceLink object is nul");
        } else {
            if (dataCenterServerServiceLink.getService() == null) {
                errorList.add("Service object is nul");
            } else {
                if (dataCenterServerServiceLink.getDataCenterServer() == null) {
                    errorList.add("Server object is nul");
                } else {
                    if (!this.dataCenterServerServiceLinkService.existsById(dataCenterServerServiceLink.getServerServiceLinkId())) {
                        errorList.add("ServerServiceLink Id not defined");
                    }

                    if (!this.dataCenterServerService.existsById(dataCenterServerServiceLink.getDataCenterServer().getServerId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (!this.serviceService.existsById(dataCenterServerServiceLink.getService().getServiceId())) {
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
    public ValidateObject deleteItem(DataCenterServerServiceLink dataCenterServerServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerServiceLink == null) {
            errorList.add("ServerServiceLink object is nul");
        } else {
            if (!this.dataCenterServerServiceLinkService.existsById(dataCenterServerServiceLink.getServerServiceLinkId())) {
                errorList.add("ServerServiceLink Id not defined");
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
    public ValidateObject findOne(DataCenterServerServiceLink dataCenterServerServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerServiceLink == null) {
            errorList.add("ServerServiceLink object is nul");
        } else {
            if (!this.dataCenterServerServiceLinkService.existsById(dataCenterServerServiceLink.getServerServiceLinkId())) {
                errorList.add("ServerServiceLink Id not defined");
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
    public ValidateObject findById(DataCenterServerServiceLink dataCenterServerServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerServiceLink == null) {
            errorList.add("ServerServiceLink object is nul");
        } else {
            if (!this.dataCenterServerServiceLinkService.existsById(dataCenterServerServiceLink.getServerServiceLinkId())) {
                errorList.add("ServerServiceLink Id not defined");
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
