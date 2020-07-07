package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class AdminRoleValidate implements ValidateInterface<AdminRole> {
    private AdminRoleService adminRoleService;

    @Autowired
    public AdminRoleValidate(AdminRoleService adminRoleService) {
        this.adminRoleService = adminRoleService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminRole adminRole) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(AdminRole adminRole) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(AdminRole adminRole) {
        return null;
    }

    @Override
    public ValidateObject findOne(AdminRole adminRole) {
        return null;
    }

    @Override
    public ValidateObject findById(AdminRole adminRole) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
