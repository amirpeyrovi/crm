package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.service.AdminRolePermissionService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminRolePermissionValidate implements ValidateInterface<AdminRolePermission> {
    private AdminRolePermissionService adminRolePermissionService;

    @Autowired
    public AdminRolePermissionValidate(AdminRolePermissionService adminRolePermissionService) {
        this.adminRolePermissionService = adminRolePermissionService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminRolePermission adminRolePermission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRolePermission == null){
            errorList.add("Admin Role information is required");
        }else{
            if(adminRolePermission.getTitle() == null || adminRolePermission.getTitle().isEmpty() ){
                errorList.add("Title is required");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(AdminRolePermission adminRolePermission) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(AdminRolePermission adminRolePermission) {
        return null;
    }

    @Override
    public ValidateObject findOne(AdminRolePermission adminRolePermission) {
        return null;
    }

    @Override
    public ValidateObject findById(AdminRolePermission adminRolePermission) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
