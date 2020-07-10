package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfig;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterServerConfigValidate implements ValidateInterface<DataCenterServerConfig> {
    private DataCenterServerConfigService dataCenterServerConfigService;
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;

    @Autowired
    public DataCenterServerConfigValidate(DataCenterServerConfigService dataCenterServerConfigService, DataCenterServerConfigGroupService dataCenterServerConfigGroupService) {
        this.dataCenterServerConfigService = dataCenterServerConfigService;
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterServerConfig dataCenterServerConfig) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfig == null) {
            errorList.add("object is nul");
        } else {
            if (dataCenterServerConfig.getDataCenterServerConfigGroup() == null) {
                errorList.add("object is nul");
            } else {
                if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfig.getDataCenterServerConfigGroup().getConfigGroupId())) {
                    errorList.add("ServerConfig Id not defined");
                }

                if (dataCenterServerConfig.getTitle() == null || dataCenterServerConfig.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (dataCenterServerConfig.getType() == null || dataCenterServerConfig.getType().isEmpty()) {
                    errorList.add("Product Group is required");
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
    public ValidateObject validateUpdateItem(DataCenterServerConfig dataCenterServerConfig) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfig == null) {
            errorList.add("ServerConfig object is nul");
        } else {
            if (dataCenterServerConfig.getDataCenterServerConfigGroup() == null) {
                errorList.add("ServerConfigGroup object is nul");
            } else {
                if (!this.dataCenterServerConfigService.existsById(dataCenterServerConfig.getConfigId())) {
                    errorList.add("ServerConfig Id not defined");
                }

                if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfig.getDataCenterServerConfigGroup().getConfigGroupId())) {
                    errorList.add("ServerConfig Id not defined");
                }

                if (dataCenterServerConfig.getTitle() == null || dataCenterServerConfig.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (dataCenterServerConfig.getType() == null || dataCenterServerConfig.getType().isEmpty()) {
                    errorList.add("Type is required");
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
    public ValidateObject deleteItem(DataCenterServerConfig dataCenterServerConfig) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfig == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerConfigService.existsById(dataCenterServerConfig.getConfigId())) {
                errorList.add("ServerConfig Id not defined");
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
    public ValidateObject findOne(DataCenterServerConfig dataCenterServerConfig) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfig == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerConfigService.existsById(dataCenterServerConfig.getConfigId())) {
                errorList.add("ServerConfig Id not defined");
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
    public ValidateObject findById(DataCenterServerConfig dataCenterServerConfig) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfig == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerConfigService.existsById(dataCenterServerConfig.getConfigId())) {
                errorList.add("ServerConfig Id not defined");
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
