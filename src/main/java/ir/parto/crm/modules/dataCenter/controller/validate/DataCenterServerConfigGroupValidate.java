package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigGroup;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterServerConfigGroupValidate implements ValidateInterface<DataCenterServerConfigGroup> {
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;

    @Autowired
    public DataCenterServerConfigGroupValidate(DataCenterServerConfigGroupService dataCenterServerConfigGroupService) {
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigGroup == null) {
            errorList.add("ServerConfigGroup object is nul");
        } else {
            if (dataCenterServerConfigGroup.getTitle() == null || dataCenterServerConfigGroup.getTitle().isEmpty()) {
                errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigGroup == null) {
            errorList.add("ServerConfigGroup object is nul");
        } else {
            if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigGroup.getConfigGroupId())) {
                errorList.add("ServerConfigGroup Id not defined");
            }

            if (dataCenterServerConfigGroup.getTitle() == null || dataCenterServerConfigGroup.getTitle().isEmpty()) {
                errorList.add("Title is required");
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
    public ValidateObject deleteItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigGroup == null) {
            errorList.add("ServerConfigGroup object is nul");
        } else {
            if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigGroup.getConfigGroupId())) {
                errorList.add("ServerConfigGroup Id not defined");
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
    public ValidateObject findOne(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigGroup == null) {
            errorList.add("ServerConfigGroup object is nul");
        } else {
            if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigGroup.getConfigGroupId())) {
                errorList.add("ServerConfigGroup Id not defined");
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
    public ValidateObject findById(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServerConfigGroup == null) {
            errorList.add("ServerConfigGroup object is nul");
        } else {
            if (!this.dataCenterServerConfigGroupService.existsById(dataCenterServerConfigGroup.getConfigGroupId())) {
                errorList.add("ServerConfigGroup Id not defined");
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
