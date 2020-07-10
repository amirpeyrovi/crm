package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenter;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterValidate implements ValidateInterface<DataCenter> {
    private DataCenterService dataCenterService;

    @Autowired
    public DataCenterValidate(DataCenterService dataCenterService) {
        this.dataCenterService = dataCenterService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenter dataCenter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenter == null) {
            errorList.add("object is nul");
        } else {
            if (dataCenter.getTitle() == null || dataCenter.getTitle().isEmpty()) {
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
    public ValidateObject validateUpdateItem(DataCenter dataCenter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenter == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterService.existsById(dataCenter.getDataCenterId())) {
                errorList.add("DataCenter Id not defined");
            }

            if (dataCenter.getTitle() == null || dataCenter.getTitle().isEmpty()) {
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
    public ValidateObject deleteItem(DataCenter dataCenter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenter == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterService.existsById(dataCenter.getDataCenterId())) {
                errorList.add("DataCenter Id not defined");
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
    public ValidateObject findOne(DataCenter dataCenter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenter == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterService.existsById(dataCenter.getDataCenterId())) {
                errorList.add("DataCenter Id not defined");
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
    public ValidateObject findById(DataCenter dataCenter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenter == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterService.existsById(dataCenter.getDataCenterId())) {
                errorList.add("DataCenter Id not defined");
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
