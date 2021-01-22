package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminPermissionValidate implements ValidateInterface<AdminPermission> {
    private AdminPermissionService adminPermissionService;

    @Autowired
    public AdminPermissionValidate(AdminPermissionService adminPermissionService) {
        this.adminPermissionService = adminPermissionService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminPermission adminPermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminPermission == null) {
            errorList.add("Admin information is required");
        } else {
            if (adminPermission.getTitle() == null || adminPermission.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            } else {
                AdminPermission exist = this.adminPermissionService.findByTitle(adminPermission.getTitle());
                if (exist != null) {
                    errorList.add("Title must be unique");
                }
            }

            if (adminPermission.getShowName() == null || adminPermission.getShowName().isEmpty()) {
                errorList.add("ShowName is required");
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
    public ValidateObject validateUpdateItem(AdminPermission adminPermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminPermission == null) {
            errorList.add("AdminPermission information is required");
        } else {
            if (!this.adminPermissionService.existsById(adminPermission.getPermissionId())) {
                errorList.add("AdminPermission is not defined!");
            }

            if (adminPermission.getTitle() != null && adminPermission.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            } else {
                AdminPermission exist = this.adminPermissionService.findByTitle(adminPermission.getTitle());
                if (exist != null && !exist.getPermissionId().equals(adminPermission.getPermissionId())) {
                    errorList.add("Title must be unique");
                }
            }

            if (adminPermission.getShowName() == null || adminPermission.getShowName().isEmpty()) {
                errorList.add("ShowName is required");
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
    public ValidateObject deleteItem(AdminPermission adminPermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminPermission == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminPermissionService.existsById(adminPermission.getPermissionId())) {
                errorList.add("Admin is not defined!");
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
    public ValidateObject findOne(AdminPermission adminPermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminPermission == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminPermissionService.existsById(adminPermission.getPermissionId())) {
                errorList.add("Admin is not defined!");
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
    public ValidateObject findById(AdminPermission adminPermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminPermission == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminPermissionService.existsById(adminPermission.getPermissionId())) {
                errorList.add("Admin is not defined!");
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
