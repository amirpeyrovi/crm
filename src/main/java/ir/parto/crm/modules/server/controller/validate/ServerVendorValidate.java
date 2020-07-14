package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.ServerVendor;
import ir.parto.crm.modules.server.model.service.ServerVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServerVendorValidate implements ValidateInterface<ServerVendor> {
    private ServerVendorService serverVendorService;

    @Override
    public ValidateObject validateAddNewItem(ServerVendor serverVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverVendor == null) {
            errorList.add("object is nul");
        } else {
            if (serverVendor.getTitle() == null || serverVendor.getTitle().isEmpty()) {
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
    public ValidateObject validateUpdateItem(ServerVendor serverVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverVendor == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serverVendorService.existsById(serverVendor.getServerVendorId())) {
                errorList.add("ServerVendor Id not defined");
            }

            if (serverVendor.getTitle() == null || serverVendor.getTitle().isEmpty()) {
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
    public ValidateObject deleteItem(ServerVendor serverVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverVendor == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serverVendorService.existsById(serverVendor.getServerVendorId())) {
                errorList.add("Product Id not defined");
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
    public ValidateObject findOne(ServerVendor serverVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverVendor == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serverVendorService.existsById(serverVendor.getServerVendorId())) {
                errorList.add("Product Id not defined");
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
    public ValidateObject findById(ServerVendor serverVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverVendor == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serverVendorService.existsById(serverVendor.getServerVendorId())) {
                errorList.add("Product Id not defined");
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
