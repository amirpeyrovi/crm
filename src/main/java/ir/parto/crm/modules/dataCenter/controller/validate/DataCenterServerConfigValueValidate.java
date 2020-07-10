package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigValue;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigValueService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterServerConfigValueValidate implements ValidateInterface<DataCenterServerConfigValue> {
    private DataCenterServerConfigValueService dataCenterServerConfigValueService;
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;
    private DataCenterServerService dataCenterServerService;

    @Autowired
    public DataCenterServerConfigValueValidate(DataCenterServerConfigValueService dataCenterServerConfigValueService, DataCenterServerConfigGroupService dataCenterServerConfigGroupService, DataCenterServerService dataCenterServerService) {
        this.dataCenterServerConfigValueService = dataCenterServerConfigValueService;
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
        this.dataCenterServerService = dataCenterServerService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterServerConfigValue dataCenterServerConfigValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigValue == null) {
            errorList.add("ServerConfigValue object is nul");
        } else {
            if (dataCenterServerConfigValue.getDataCenterServer() == null) {
                errorList.add("Server object is nul");
            } else {
                if (dataCenterServerConfigValue.getDataCenterServerConfigGroup() == null) {
                    errorList.add("ServerConfigGroup object is nul");
                } else {
                    if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigValue.getDataCenterServerConfigGroup().getConfigGroupId())) {
                        errorList.add("ServerConfigValue Id not defined");
                    }

                    if (!this.dataCenterServerService.existsById(dataCenterServerConfigValue.getDataCenterServer().getServerId())) {
                        errorList.add("ServerConfigValue Id not defined");
                    }

                    if (dataCenterServerConfigValue.getValue() == null) {
                        errorList.add("Value is required");
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
    public ValidateObject validateUpdateItem(DataCenterServerConfigValue dataCenterServerConfigValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigValue == null) {
            errorList.add("ServerConfigValue object is nul");
        } else {
            if (dataCenterServerConfigValue.getDataCenterServer() == null) {
                errorList.add("Server object is nul");
            } else {
                if (dataCenterServerConfigValue.getDataCenterServerConfigGroup() == null) {
                    errorList.add("ServerConfigGroup object is nul");
                } else {
                    if (!this.dataCenterServerConfigValueService.existsById(dataCenterServerConfigValue.getConfigId())) {
                        errorList.add("ServerConfigValue Id not defined");
                    }

                    if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigValue.getDataCenterServerConfigGroup().getConfigGroupId())) {
                        errorList.add("ServerConfigValue Id not defined");
                    }

                    if (!this.dataCenterServerService.existsById(dataCenterServerConfigValue.getDataCenterServer().getServerId())) {
                        errorList.add("ServerConfigValue Id not defined");
                    }

                    if (dataCenterServerConfigValue.getValue() == null) {
                        errorList.add("Value is required");
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
    public ValidateObject deleteItem(DataCenterServerConfigValue dataCenterServerConfigValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigValue == null) {
            errorList.add("ServerConfigValue object is nul");
        } else {
            if (!this.dataCenterServerConfigValueService.existsById(dataCenterServerConfigValue.getConfigId())) {
                errorList.add("ServerConfigValue Id not defined");
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
    public ValidateObject findOne(DataCenterServerConfigValue dataCenterServerConfigValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigValue == null) {
            errorList.add("ServerConfigValue object is nul");
        } else {
            if (!this.dataCenterServerConfigValueService.existsById(dataCenterServerConfigValue.getConfigId())) {
                errorList.add("ServerConfigValue Id not defined");
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
    public ValidateObject findById(DataCenterServerConfigValue dataCenterServerConfigValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigValue == null) {
            errorList.add("ServerConfigValue object is nul");
        } else {
            if (!this.dataCenterServerConfigValueService.existsById(dataCenterServerConfigValue.getConfigId())) {
                errorList.add("ServerConfigValue Id not defined");
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
