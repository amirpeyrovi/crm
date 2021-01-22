package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRolePermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminRolePermissionValidate implements ValidateInterface<AdminRolePermission> {
    private AdminRolePermissionService adminRolePermissionService;
    private AdminRoleService adminRoleService;
    private AdminPermissionService adminPermissionService;

    @Autowired
    public AdminRolePermissionValidate(AdminRolePermissionService adminRolePermissionService, AdminRoleService adminRoleService, AdminPermissionService adminPermissionService) {
        this.adminRolePermissionService = adminRolePermissionService;
        this.adminRoleService = adminRoleService;
        this.adminPermissionService = adminPermissionService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminRolePermission == null) {
            errorList.add("Admin Role information is required");
        } else {
            if (adminRolePermission.getTitle() == null || adminRolePermission.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            } else {
                AdminRolePermission adminRPUnique = this.adminRolePermissionService.findByTitle(adminRolePermission.getTitle());
                if (adminRPUnique != null && adminRPUnique.getAdminRolePermissionId() != null && adminRPUnique.getAdminRolePermissionId() != 0) {
                    errorList.add("Title must be unique");
                }
            }

            if (adminRolePermission.getAdminRole() == null || adminRolePermission.getAdminRole().getAdminRoleId() == null || adminRolePermission.getAdminRole().getAdminRoleId() == 0) {
                errorList.add("Admin Role is required");
            }

            if (adminRolePermission.getAdminPermission() == null || adminRolePermission.getAdminPermission().getPermissionId() == null || adminRolePermission.getAdminPermission().getPermissionId() == 0) {
                errorList.add("Admin Permission is required");
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
    public ValidateObject validateUpdateItem(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminRolePermission == null) {
            errorList.add("AdminRole Permission information is required");
        } else {
            if (!this.adminRolePermissionService.existsById(adminRolePermission.getAdminRolePermissionId())) {
                errorList.add("Admin is not defined!");
            }

            if (adminRolePermission.getAdminRole() != null && (
                    adminRolePermission.getAdminRole().getAdminRoleId() == null ||
                            adminRolePermission.getAdminRole().getAdminRoleId() == 0)) {
                errorList.add("AdminRole is required");
            } else {
                if (!this.adminRoleService.existsById(adminRolePermission.getAdminRole().getAdminRoleId())) {
                    errorList.add("Role not found");
                }
            }

            if (adminRolePermission.getAdminPermission() == null || adminRolePermission.getAdminPermission().getPermissionId() == null || adminRolePermission.getAdminPermission().getPermissionId() == 0) {
                errorList.add("AdminPermission is required");
            } else {
                if (!this.adminPermissionService.existsById(adminRolePermission.getAdminPermission().getPermissionId())) {
                    errorList.add("Admin Permission not found");
                }
            }
            if (adminRolePermission.getTitle() != null && adminRolePermission.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            } else if (adminRolePermission.getTitle() != null) {
                AdminRolePermission adminRPUnique = this.adminRolePermissionService.findByTitle(adminRolePermission.getTitle());
                if (adminRPUnique != null &&
                        adminRPUnique.getAdminRolePermissionId() != null && adminRPUnique.getAdminRolePermissionId() != 0 && !adminRPUnique.getAdminRolePermissionId().equals(adminRolePermission.getAdminRolePermissionId())) {
                    errorList.add("Title must be unique");
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
    public ValidateObject deleteItem(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminRolePermission == null || adminRolePermission.getAdminRolePermissionId() == null || adminRolePermission.getAdminRolePermissionId() == 0) {
            errorList.add("Admin Role Permission information is required");
        } else {
            if (!this.adminRolePermissionService.existsById(adminRolePermission.getAdminRolePermissionId())) {
                errorList.add("Admin Role Permission is not defined!");
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
    public ValidateObject findOne(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminRolePermission == null || adminRolePermission.getAdminRolePermissionId() == null || adminRolePermission.getAdminRolePermissionId() == 0) {
            errorList.add("Admin Role Permission information is required");
        } else {
            if (!this.adminRolePermissionService.existsById(adminRolePermission.getAdminRolePermissionId())) {
                errorList.add("Admin Role Permission is not defined!");
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
    public ValidateObject findById(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (adminRolePermission == null || adminRolePermission.getAdminRolePermissionId() == null || adminRolePermission.getAdminRolePermissionId() == 0) {
            errorList.add("Admin Role Permission information is required");
        } else {
            if (!this.adminRolePermissionService.existsById(adminRolePermission.getAdminRolePermissionId())) {
                errorList.add("Admin Role Permission is not defined!");
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
