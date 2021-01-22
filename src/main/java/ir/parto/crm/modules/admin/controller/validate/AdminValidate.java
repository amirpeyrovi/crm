package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminValidate implements ValidateInterface<Admin> {
    private AdminService adminService;
    private AdminRoleService adminRoleService;

    @Autowired
    public AdminValidate(AdminService adminService, AdminRoleService adminRoleService) {
        this.adminService = adminService;
        this.adminRoleService = adminRoleService;
    }

    @Override
    public ValidateObject validateAddNewItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (admin == null) {
            errorList.add("Admin information is required");
        } else {
            if (admin.getFirstName() == null || admin.getFirstName().isEmpty()) {
                errorList.add("First Name is required");
            }

            if (admin.getLastName() == null || admin.getLastName().isEmpty()) {
                errorList.add("Last Name is required");
            }

            if (admin.getUsername() == null || admin.getUsername().isEmpty()) {
                errorList.add("Username is required");
            } else {
                Admin exist = this.adminService.findByUsername(admin.getUsername());
                if (exist != null) {
                    errorList.add("Username must be unique");
                }
            }

            if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
                errorList.add("Password is required");
            }

            if (admin.getIdentifyCode() == null || admin.getIdentifyCode().isEmpty()) {
                errorList.add("IdentifyCode is required");
            }

            if (admin.getAdminRole() == null || admin.getAdminRole().getAdminRoleId() == null || admin.getAdminRole().getAdminRoleId() == 0) {
                errorList.add("AdminRole is required");
            } else {
                if (!this.adminRoleService.existsById(admin.getAdminRole().getAdminRoleId())) {
                    errorList.add("Role not found");
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
    public ValidateObject validateUpdateItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (admin == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminService.existsById(admin.getAdminId())) {
                errorList.add("Admin is not defined!");
            } else {
                if (admin.getAdminRole() == null || admin.getAdminRole().getAdminRoleId() == null || admin.getAdminRole().getAdminRoleId() == 0) {
                    errorList.add("AdminRole is required");
                } else {
                    if (!this.adminRoleService.existsById(admin.getAdminRole().getAdminRoleId())) {
                        errorList.add("Role not found");
                    }
                }
                if (admin.getUsername() != null && admin.getUsername().isEmpty()) {
                    errorList.add("Username must not be null");
                } else {
                    Admin exist = this.adminService.findByUsername(admin.getUsername());
                    if (exist != null && !admin.getAdminId().equals(exist.getAdminId())) {
                        errorList.add("Username must be unique");
                    }
                }

                if(admin.getPassword() != null && admin.getPassword().isEmpty()){
                    errorList.add("Password must not be null");
                }

                if(admin.getFirstName() != null && admin.getFirstName().isEmpty()){
                    errorList.add("FirstName must not be null");
                }

                if(admin.getLastName() != null && admin.getLastName().isEmpty()){
                    errorList.add("LastName must not be null");
                }

                if(admin.getIdentifyCode() != null && admin.getIdentifyCode().isEmpty()){
                    errorList.add("IdentifyCode must not be null");
                }

                if(admin.getAdminRole() != null && (admin.getAdminRole().getAdminRoleId() == null || admin.getAdminRole().getAdminRoleId() == 0)){
                    errorList.add("Role must not be null");
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
    public ValidateObject deleteItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (admin == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminService.existsById(admin.getAdminId())) {
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
    public ValidateObject findOne(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (admin == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminService.existsById(admin.getAdminId())) {
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
    public ValidateObject findById(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (admin == null) {
            errorList.add("Admin information is required");
        } else {
            if (!this.adminService.existsById(admin.getAdminId())) {
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
