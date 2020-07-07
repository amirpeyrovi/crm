package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class AdminValidate implements ValidateInterface<Admin> {
    private AdminService adminService;

    @Autowired
    public AdminValidate(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public ValidateObject validateAddNewItem(Admin admin) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(Admin admin) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(Admin admin) {
        return null;
    }

    @Override
    public ValidateObject findOne(Admin admin) {
        return null;
    }

    @Override
    public ValidateObject findById(Admin admin) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
