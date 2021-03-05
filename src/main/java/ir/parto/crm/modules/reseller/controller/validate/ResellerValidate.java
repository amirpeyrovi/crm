package ir.parto.crm.modules.reseller.controller.validate;

import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ResellerValidate implements ValidateInterface<Reseller> {
    private ResellerService resellerService;
    private AdminService adminService;

    @Autowired
    public ResellerValidate(ResellerService resellerService, AdminService adminService) {
        this.resellerService = resellerService;
        this.adminService = adminService;
    }

    @Override
    public ValidateObject validateAddNewItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (reseller.getAdmin() == null) {
                errorList.add("Admin object is nul");
            } else {
                if (!this.adminService.existsById(reseller.getAdmin().getAdminId())) {
                    errorList.add("Admin Id not defined");
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
    public ValidateObject validateUpdateItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (reseller.getAdmin() == null) {
                errorList.add("Client object is nul");
            } else {
                if (!this.resellerService.existsById(reseller.getResellerId())) {
                    errorList.add("Reseller Id not defined");
                }

                if (!this.adminService.existsById(reseller.getAdmin().getAdminId())) {
                    errorList.add("Admin Id not defined");
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
    public ValidateObject deleteItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
    public ValidateObject findOne(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
    public ValidateObject findById(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
