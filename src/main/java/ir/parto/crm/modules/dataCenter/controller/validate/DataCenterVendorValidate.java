package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterVendorValidate implements ValidateInterface<DataCenterVendor> {
    private DataCenterVendorService dataCenterVendorService;

    @Autowired
    public DataCenterVendorValidate(DataCenterVendorService dataCenterVendorService) {
        this.dataCenterVendorService = dataCenterVendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterVendor dataCenterVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterVendor == null) {
            errorList.add("Vendor object is null");
        } else {
//            if (!this.dataCenterVendorService.existsById(dataCenterVendor.getVendorId())) {
//                errorList.add("Vendor Id not defined");
//            }

            if (dataCenterVendor.getTitle() == null || dataCenterVendor.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }

            if (dataCenterVendor.getType() == null || dataCenterVendor.getType() == 0) {
                errorList.add("Type is required");
            }

            if (dataCenterVendor.getModel() == null || dataCenterVendor.getModel().isEmpty()) {
                errorList.add("Model is required");
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
    public ValidateObject validateUpdateItem(DataCenterVendor dataCenterVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterVendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.dataCenterVendorService.existsById(dataCenterVendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
            }

            if (dataCenterVendor.getTitle() == null || dataCenterVendor.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }

            if (dataCenterVendor.getType() == null || dataCenterVendor.getType() == 0) {
                errorList.add("Type is required");
            }

            if (dataCenterVendor.getModel() == null || dataCenterVendor.getModel().isEmpty()) {
                errorList.add("Model is required");
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
    public ValidateObject deleteItem(DataCenterVendor dataCenterVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterVendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.dataCenterVendorService.existsById(dataCenterVendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
    public ValidateObject findOne(DataCenterVendor dataCenterVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterVendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.dataCenterVendorService.existsById(dataCenterVendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
    public ValidateObject findById(DataCenterVendor dataCenterVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterVendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.dataCenterVendorService.existsById(dataCenterVendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
