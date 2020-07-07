package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class AdminPermissionValidate implements ValidateInterface<AdminPermission> {
    private AdminPermissionService adminPermissionService;

    @Autowired
    public AdminPermissionValidate(AdminPermissionService adminPermissionService) {
        this.adminPermissionService = adminPermissionService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminPermission adminPermission) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(AdminPermission adminPermission) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(AdminPermission adminPermission) {
        return null;
    }

    @Override
    public ValidateObject findOne(AdminPermission adminPermission) {
        return null;
    }

    @Override
    public ValidateObject findById(AdminPermission adminPermission) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
